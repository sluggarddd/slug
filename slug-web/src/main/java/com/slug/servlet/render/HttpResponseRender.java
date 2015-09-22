package com.slug.servlet.render;

import com.slug.servlet.support.HttpRequestContext;

import java.util.Map;

/**
 * @author zhw
 * @version 0.1  15/9/22
 */
public interface HttpResponseRender {


    public void preRender(HttpRequestContext context, Map<String, Object> args);


    public void render(HttpRequestContext context);


    public void postRender(HttpRequestContext context, Object ret);

}
