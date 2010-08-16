package uk.mafu.loon.mvc;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import uk.mafu.loon.domain.data.LoonPdf;
import uk.mafu.loon.services.DataService;

public class PdfPreviewController extends AbstractController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PdfPreviewController.class);
	private DataService dataService;
	private static final int BUF_SIZE = 8192;

	public DataService getDataService() {
		return dataService;
	}

	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer pdfId = extractPdfid(request);
		LoonPdf pdf = getDataService().getPdfByPk(pdfId);
		int length = (int) pdf.getData().length;
		response.setContentLength(length);
		int responseBufferSize = response.getBufferSize();
		if (0 == responseBufferSize) {
			responseBufferSize = BUF_SIZE;
		}
		ServletOutputStream out = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + pdf.getFilename() + "\"");
		if (logger.isDebugEnabled()) {
			logger
					.debug("handleRequestInternal(HttpServletRequest, HttpServletResponse) - Content length ='" + pdf.getData().length + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		out.write(pdf.getData());
		out.flush();
		out.close();
		return null;
	}

	private Integer extractPdfid(HttpServletRequest request) {
		try {
			return new Integer(request.getParameter("pdfId"));
		} catch (NumberFormatException e) {
			throw new UnsupportedOperationException("missing pk parameter");
		}
	}
}
