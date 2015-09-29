package com.slug.servlet.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhw
 * @version 0.1  15/9/29
 */
public interface HandlerException {

    /**
     * deal with exception
     *
     * @param request
     * @param response
     * @param e
     */
    void handleException(HttpServletRequest request, HttpServletResponse response, Exception e);
}
