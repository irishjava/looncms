package uk.mafu.loon.mvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class UploadExceedsMaxSizeHandler implements HandlerExceptionResolver, Ordered {
	protected final Log logger = LogFactory.getLog(getClass());
	private int order;

	/**
	 * <p>
	 * Handles all unhandled ApplicationErrors
	 * </p>
	 * <p>
	 * Displays error messages and a back link
	 * </p>
	 */
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception e) {
		if (e instanceof MaxUploadSizeExceededException) {
			MaxUploadSizeExceededException x = (MaxUploadSizeExceededException) e;
			ModelAndView modelAndView = new ModelAndView("images/fileUploadComplete");
			modelAndView.addObject("status", "failure");
			modelAndView.addObject("imageLink", null);
			modelAndView.addObject("message", "Maximum file upload size has been exceeded,max size is:"
					+ x.getMaxUploadSize());
			return modelAndView;
		} else {
			return null;
		}
	}

	/**
	 * Used by spring configuration to decide the order that the handler will be
	 * invokes in relation to other handlers
	 */
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}