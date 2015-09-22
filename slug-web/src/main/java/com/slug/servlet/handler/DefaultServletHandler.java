package com.slug.servlet.handler;

import com.slug.utils.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhw
 * @version 0.1  15/9/22
 */
public class DefaultServletHandler implements Handler {

    /**
     * Default Servlet name used by Tomcat, Jetty, JBoss, and GlassFish
     */
    private static final String COMMON_DEFAULT_SERVLET_NAME = "default";

    /**
     * Default Servlet name used by Google App Engine
     */
    private static final String GAE_DEFAULT_SERVLET_NAME = "_ah_default";

    /**
     * Default Servlet name used by Resin
     */
    private static final String RESIN_DEFAULT_SERVLET_NAME = "resin-file";

    /**
     * Default Servlet name used by WebLogic
     */
    private static final String WEBLOGIC_DEFAULT_SERVLET_NAME = "FileServlet";

    /**
     * Default Servlet name used by WebSphere
     */
    private static final String WEBSPHERE_DEFAULT_SERVLET_NAME = "SimpleFileServlet";

    /**
     * default-servlet name for logger
     */
    private String defaultServletName;


    private ServletContext servletContext;


    public DefaultServletHandler(ServletContext servletContext) {

        this.servletContext = servletContext;

        if (!StringUtils.hasText(this.defaultServletName)) {
            if (servletContext.getNamedDispatcher(COMMON_DEFAULT_SERVLET_NAME) != null) {
                this.defaultServletName = COMMON_DEFAULT_SERVLET_NAME;
            } else if (servletContext.getNamedDispatcher(GAE_DEFAULT_SERVLET_NAME) != null) {
                this.defaultServletName = GAE_DEFAULT_SERVLET_NAME;
            } else if (servletContext.getNamedDispatcher(RESIN_DEFAULT_SERVLET_NAME) != null) {
                this.defaultServletName = RESIN_DEFAULT_SERVLET_NAME;
            } else if (servletContext.getNamedDispatcher(WEBLOGIC_DEFAULT_SERVLET_NAME) != null) {
                this.defaultServletName = WEBLOGIC_DEFAULT_SERVLET_NAME;
            } else if (servletContext.getNamedDispatcher(WEBSPHERE_DEFAULT_SERVLET_NAME) != null) {
                this.defaultServletName = WEBSPHERE_DEFAULT_SERVLET_NAME;
            } else {
                throw new IllegalStateException("Unable to locate the default servlet for serving static content. " +
                        "Please set the 'defaultServletName' property explicitly.");
            }

        }

    }


    public void handler(HttpServletRequest request, HttpServletResponse response) throws Exception {

        RequestDispatcher requestDispatcher = this.servletContext.getNamedDispatcher(this.defaultServletName);
        if (requestDispatcher == null) {
            throw new IllegalStateException("A RequestDispatcher could not be located for the default servlet '" +
                    this.defaultServletName + "'");
        }

        requestDispatcher.forward(request, response);
    }
}
