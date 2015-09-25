package com.slug.servlet;

/**
 * @author zhw
 * @version 0.1  15/9/25
 */
public class Requester {

    private String requestMethod;
    private String requestPath;

    public Requester(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }
}
