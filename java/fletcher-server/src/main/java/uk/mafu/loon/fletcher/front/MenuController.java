package uk.mafu.loon.fletcher.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import uk.mafu.loon.fletcher.front.FletcherService;

public class MenuController extends AbstractController implements InitializingBean {
	private FletcherService fletcherService;

	public void setFletcherService(FletcherService fletcherService) {
		this.fletcherService = fletcherService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ModelAndView modelAndView = new ModelAndView("menu", "fletcherService", fletcherService);
		return modelAndView;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.notNull(fletcherService);
	}
}
