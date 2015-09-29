package com.slug.servlet;

import com.slug.servlet.handler.HandlerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhw
 * @version 0.1  15/9/29
 */
public class DefaultHandlerException implements HandlerException {

    @Override
    public void handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {

        //the exception why
        Throwable cause = e.getCause();

        if (cause == null) {
            //todo add logo
            return;
        }


    }

}
