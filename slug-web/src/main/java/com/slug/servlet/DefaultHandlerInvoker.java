package com.slug.servlet;

import com.slug.ApplicationContext;
import com.slug.servlet.handler.HandlerInvoker;
import com.slug.servlet.render.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handler调用器
 *
 * @author zhw
 * @version 0.1  15/9/28
 */
public class DefaultHandlerInvoker implements HandlerInvoker {

    private ViewResolver viewResolver = ApplicationContext.getViewResolver();

    @Override
    public void invokeHandler(HttpServletRequest request, HttpServletResponse response, Hamal hamal) throws Exception {

    }
}
