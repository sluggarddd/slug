package com.slug.servlet;

import com.slug.servlet.render.ViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhanghw on 2015/9/28.
 */
public class DefaultViewResolver implements ViewResolver {


    @Override
    public void resolveView(HttpServletRequest request, HttpServletResponse response, Object controllerResult) {

        if (controllerResult != null) {

            
        }

    }
}
