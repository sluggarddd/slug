package com.slug.servlet;

import com.slug.GlobalConfig;
import com.slug.servlet.render.Result;
import com.slug.servlet.render.View;
import com.slug.servlet.render.ViewResolver;
import com.slug.utils.CollectionUtils;
import com.slug.utils.MapUtils;
import com.slug.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by zhanghw on 2015/9/28.
 */
public class DefaultViewResolver implements ViewResolver {


    @Override
    public void resolveView(HttpServletRequest request, HttpServletResponse response, Object controllerResult) {

        if (controllerResult != null) {
            // if the return is View or result
            if (controllerResult instanceof View) {
                //if is view then consider redirect or forward
                View view = (View) controllerResult;
                if (view.isRedirect()) {
                    //get view path
                    String path = view.getPath();

                    // redirect request
                    WebUtils.redirectRequest(path, request, response);

                } else {
                    String path = GlobalConfig.VIEW_PATH + view.getPath();

                    //generate request
                    Map<String, Object> data = view.getData();
                    if (MapUtils.isEmpty(data)) {
                        for (Map.Entry<String, Object> entry : data.entrySet()) {
                            request.setAttribute(entry.getKey(), entry.getValue());
                        }
                    }

                    //forward request
                    WebUtils.forwardRequest(path, request, response);

                }
            } else {

                //
                Result result = (Result) controllerResult;

                //todo deal with upload file

                WebUtils.writeJSON(response, result);

            }


        }

    }
}
