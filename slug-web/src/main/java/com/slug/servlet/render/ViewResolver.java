package com.slug.servlet.render;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhanghw on 2015/9/28.
 */
public interface ViewResolver {


    /**
     * reslove the view
     *
     * @param request
     * @param response
     * @param controllerResult
     */
    public void resolveView(HttpServletRequest request, HttpServletResponse response, Object controllerResult);


}
