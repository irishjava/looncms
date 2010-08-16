package uk.mafu.loon.mvc;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import uk.mafu.loon.domain.data.LoonVideo;
import uk.mafu.loon.services.DataService;

public class VideoViewController extends AbstractController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(VideoViewController.class);
	private DataService dataService;
	private static final int BUF_SIZE = 1 << 16;

	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer pk = extractId(request);
		LoonVideo video = getDataService().getVideoByVideoLinkPk(pk);
		response.setContentLength((int) video.getData().length);
		response.setHeader("Content-disposition", "attachment; filename=" + video.getFilename());
		if (0 == response.getBufferSize()) {
			response.setBufferSize(BUF_SIZE);
		}
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("video/x-flv");
		if (logger.isDebugEnabled()) {
			logger.debug("VideoViewController: pk='" + pk + "' filename='" + video.getFilename()
					+ "' video.data.size='" + video.getData().length + "' video.pk ='" + video.getPk());
		}
		out.write(video.getData());
		out.flush();
		out.close();
		if (logger.isDebugEnabled()) {
			logger.debug("VideoViewController: Finished outputting video pk='" + pk + "' filename='" + video.getFilename()
					+ "' video.data.size='" + video.getData().length + "' video.pk ='" + video.getPk());
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
