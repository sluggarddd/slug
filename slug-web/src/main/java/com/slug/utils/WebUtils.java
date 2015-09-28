package com.slug.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhw
 * @version 0.1  15/9/28
 */
public class WebUtils {

    public static String getRequestPath(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String pathInfo = StringUtils.isEmpty(request.getPathInfo(), "");
        return servletPath + pathInfo;
    }
}
