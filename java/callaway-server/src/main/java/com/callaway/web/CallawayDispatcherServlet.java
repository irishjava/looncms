package com.callaway.web;

import com.callaway.service.SessionHolder;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class CallawayDispatcherServlet extends DispatcherServlet {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
            .getLogger(CallawayDispatcherServlet.class);

    private static final long serialVersionUID = 1L;

    /**
     * What we do here is that we put the HttpSession into the SessionHolder
     * which holds it within a ThreadLocal.
     * <p/>
     * The session will thus be available to the CallawayService, but without
     * any use of static accessors or other naff aproaches which would render
     * the service untestable.
     * <p/>
     * Now we can just mock this out during testing. If all else fails we can
     * embed the code to access the WebApplicationContext behind the facade of
     * the SessionHolder.
     */
    @Override
    protected void doService(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        SessionHolder bean = (SessionHolder) getWebApplicationContext()
                .getBean("sessionHolder", SessionHolder.class);
        HttpSession session = request.getSession(true);
        bean.setSession(session);
        bean.setIp(request.getRemoteAddr());

        logger.info("session id=:" + session.getId());
        Enumeration attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = (String) attributeNames.nextElement();
            logger.info(
                    "':" + session.getId() + ":'"
                            + name + "':'"
                            + session.getAttribute(name) + "'"
            );
        }


        super.doService(request, response);
    }


}