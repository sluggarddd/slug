package com.slug.servlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhw
 * @version 0.1  15/9/23
 */
public class HandlerExecutionChain {


    private final Object handler;

    private HandlerInterceptor[] interceptors;

    private List<HandlerInterceptor> interceptorList;

    private int interceptorIndex = -1;

    public HandlerExecutionChain(Object handler) {
        this(handler, null);
    }

    public HandlerExecutionChain(Object handler, HandlerInterceptor[] interceptors) {
        if (handler instanceof HandlerExecutionChain) {
            HandlerExecutionChain originalChain = (HandlerExecutionChain) handler;
        }

        this.handler = handler;
    }

    public Object getHandler() {
        return this.handler;
    }

    public void addInterceptor(HandlerInterceptor handlerInterceptor) {

    }

    private void initInterceptorList() {
        if (this.interceptorList == null) {
            this.interceptorList = new ArrayList<HandlerInterceptor>();
        }
        if (this.interceptors != null) {
            this.interceptorList.addAll(Arrays.asList(this.interceptors));
            this.interceptors = null;
        }
    }
}
