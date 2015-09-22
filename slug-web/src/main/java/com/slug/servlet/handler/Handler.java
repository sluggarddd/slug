package com.slug.servlet.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhw
 * @version 0.1  15/9/21
 */
public interface Handler {

    public void handler(HttpServletRequest request, HttpServletResponse response) throws Exception;


}
