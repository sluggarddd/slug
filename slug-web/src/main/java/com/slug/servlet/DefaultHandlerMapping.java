package com.slug.servlet;

import com.slug.servlet.adapter.URIAdaptor;
import com.slug.servlet.handler.HandlerMapping;
import com.slug.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhw
 * @version 0.1  15/9/25
 */
public class DefaultHandlerMapping implements HandlerMapping {


    public Hamal getHamal(HttpServletRequest request) throws Exception {

        String currentRequestPath = WebUtils.getRequestPath(request);

        Hamal hamal = null;

        Map<Requester, Hamal> controllerMap = URIAdaptor.getControllerMap();

        for (Map.Entry<Requester, Hamal> entry : controllerMap.entrySet()) {
            //get request from Requester
            Requester requester = entry.getKey();
            String requestMethod = requester.getRequestMethod();
            String requestPath = requester.getRequestPath();

            //get request matcher
            Matcher reqPathMatcher = Pattern.compile(requestPath).matcher(currentRequestPath);

            //judge if hte request method and path matching
            //ignore case
            if (requestMethod.equalsIgnoreCase(request.getMethod()) && reqPathMatcher.matches()) {

                //get the Hamal
                hamal = entry.getValue();

                if (hamal != null) {
                    hamal.setMatcher(reqPathMatcher);
                }

                break;

            }


        }


        return hamal;
    }


}
