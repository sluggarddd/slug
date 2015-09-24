package com.slug.servlet.handler;

import com.slug.servlet.Hamal;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhw
 * @version 0.1  15/9/24
 */
public interface HandlerMapping {

    /**
     * 将request
     *
     * @param request
     * @return
     * @throws Exception
     */
    Hamal getHamal(HttpServletRequest request) throws Exception;
}
