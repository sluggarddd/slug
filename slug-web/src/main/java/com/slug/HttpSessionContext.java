package com.slug;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * bind data in context
 *
 * @author zhw
 * @version 0.1  15/9/28
 */
public class HttpSessionContext {

    /**
     * let every thread owns its own context
     */
    private static final ThreadLocal<HttpSessionContext> contextContainer = new ThreadLocal<HttpSessionContext>();

    private HttpServletRequest request;
    private HttpServletResponse response;

    public static void init(HttpServletRequest request, HttpServletResponse response) {
        HttpSessionContext httpSessionContext = new HttpSessionContext();
        httpSessionContext.request = request;
        httpSessionContext.response = response;
        contextContainer.set(httpSessionContext);
    }



}
