package com.slug.servlet.handler;

import com.slug.servlet.Hamal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhw
 * @version 0.1  15/9/28
 */
public interface HandlerInvoker {


    /**
     * handler invoker
     *
     * @throws Exception
     */
    public void invokeHandler(HttpServletRequest request, HttpServletResponse response, Hamal hamal) throws Exception;

}
