package com.slug.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhw
 * @version 0.1  15/9/22
 */
public interface HandlerInterceptor {


    public boolean preHandler(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;




}
