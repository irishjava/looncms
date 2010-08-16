package uk.mafu.loon.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import uk.mafu.loon.domain.data.LoonImage;
import uk.mafu.loon.services.DataService;

public class ImageUploadController extends AbstractController {// extends
	private DataService dataService;

	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (!(req instanceof MultipartHttpServletRequest)) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected Multipart Request");
			return null;
		}
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
		Assert.isTrue(request.getFileNames().hasNext(), "request must contain one file");
		String name = (String) request.getFileNames().next();
		MultipartFile file = request.getFile(name);
		LoonImage loonImage = new LoonImage();
		loonImage.setData(file.getBytes());
		loonImage.setFilename(file.getOriginalFilename());
		LoonImage l = (LoonImage) getDataService().saveImage(loonImage);
		res.getWriter().write("<result>\n");
		res.getWriter().write("<status>success</status>\n");
		res.getWriter().write("<pk>" + l.getPk() + "</pk> \n");
		res.getWriter().write("<width>" + l.getWidth() + "</width>\n");
		res.getWriter().write("<height>" + l.getHeight() + "</height>\n");
		res.getWriter().write("<mimetype>" + loonImage.getMimetype() + "</mimetype> \n");
		res.getWriter().write("<filename>" + loonImage.getFilename() + "</filename> \n");
		res.getWriter().write("</result>\n");
		res.setStatus(HttpServletResponse.SC_OK);
		res.flushBuffer();
		return null;
	}
}