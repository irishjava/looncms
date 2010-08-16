package uk.mafu.loon.mvc;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import uk.mafu.loon.domain.data.LoonImage;
import uk.mafu.loon.services.BadLinkException;
import uk.mafu.loon.services.DataService;

public class ImageViewController extends AbstractController {
	//private static final int ONE_DAY = 24 * 60 * 60 * 1000;
	//private static final int ONE_YEAR = (365 * ONE_DAY);
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ImageViewController.class);
	private DataService dataService;
	private static final int BUF_SIZE = 1 << 16;

	// We just set the last modified to whatever time this servlet started up at
	// ...
	// private static long startup = new Date().getTime();
	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	// public long getLastModified(HttpServletRequest req) {
	// return startup;
	// }
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer pk = extractId(request);
		try {
			LoonImage image = getDataService().getImageByImageLinkPk(pk);
			byte[] data = image.getData();
			response.setContentLength((int) data.length);
			// response.setDateHeader("Date", System.currentTimeMillis());
			// response.setDateHeader("Expires", System.currentTimeMillis() +
			// ONE_DAY);
			// response.setDateHeader("Last-Modified",
			// System.currentTimeMillis() - ONE_YEAR);
			// response.setHeader("Cache-Control", "public,max-age=" + 3600);
			int responseBufferSize = response.getBufferSize();
			if (0 == responseBufferSize) {
				responseBufferSize = BUF_SIZE;
			}
			ServletOutputStream out = response.getOutputStream();
			response.setContentType(image.getMimetype());
			if (logger.isDebugEnabled()) {
				logger.debug("ImageView Content length ='" + data.length + "'");
			}
			out.write(data);
			out.flush();
			out.close();
		} catch (BadLinkException e) {
			response.sendError(404, "Image Not Found");
			logger.error(e.getMessage());
		}
		return null;
	}

	private Integer extractId(HttpServletRequest request) {
		try {
			return new Integer(request.getParameter("pk"));
		} catch (NumberFormatException e) {
			throw new UnsupportedOperationException("missing pk parameter");
		}
	}
}
