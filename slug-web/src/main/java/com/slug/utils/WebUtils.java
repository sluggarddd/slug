package com.slug.utils;

import com.slug.GlobalConfig;
import com.slug.servlet.config.HttpRequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhw
 * @version 0.1  15/9/28
 */
public class WebUtils {

    public static String getRequestPath(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        String pathInfo = StringUtils.isEmpty(request.getPathInfo(), "");
        return servletPath + pathInfo;
    }


    /**
     * redirect request
     *
     * @param path
     * @param request
     * @param response
     */
    public static void redirectRequest(String path, HttpServletRequest request, HttpServletResponse response) {
        try {

            response.sendRedirect(request.getContextPath() + path);
        } catch (Exception e) {
            //todo add log
            throw new RuntimeException(e);
        }
    }


    /**
     * forward request
     *
     * @param path
     * @param request
     * @param response
     */
    public static void forwardRequest(String path, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(path).forward(request, response);
        } catch (Exception e) {
            //todo add log
            throw new RuntimeException(e);
        }
    }


    /**
     * write data in json
     *
     * @param response
     * @param data
     */
    public static void writeJSON(HttpServletResponse response, Object data) {

        try {

            //set head
            response.setContentType("application/json");
            response.setCharacterEncoding(GlobalConfig.CHARACTER_ENCODING);

            PrintWriter writer = response.getWriter();
            //todo make data into json
            writer.write(data.toString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            //todo add log
            throw new RuntimeException(e);
        }

    }


    /**
     * get all param in request
     *
     * @param request
     * @return
     */
    public static Map<String, Object> getRequestParamMap(HttpServletRequest request) {

        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();

        try {
            String method = request.getMethod();
            if (method.equalsIgnoreCase("put") || method.equalsIgnoreCase("delete")) {
                String queryString = CodecUtils.decodeURL(StreamUtils.getString(request.getInputStream()));
                if (!StringUtils.isEmpty(queryString)) {
                    String[] qsArray = queryString.split("&");
                    if (!ArrayUtils.isEmpty(qsArray)) {
                        for (String qs : qsArray) {
                            String[] array = qs.split("=");
                            if (!ArrayUtils.isEmpty(array) && array.length == 2) {
                                String paramName = array[0];
                                String paramValue = array[1];

                                //todo check if necessary to check jquery param every request
                                if (checkParamName(paramName)) {
                                    if (paramMap.containsKey(paramName)) {
                                        //todo check how to override the front params
                                        paramValue = paramMap.get(paramName) + StringUtils.SEPARATOR + paramValue;
                                    }
                                    paramMap.put(paramName, paramValue);
                                }

                            }
                        }
                    }

                }
            } else {

                Enumeration<String> paramNames = request.getParameterNames();
                while (paramNames.hasMoreElements()) {
                    String paramName = paramNames.nextElement();
                    if (checkParamName(paramName)) {
                        String[] paramValues = request.getParameterValues(paramName);
                        if (!ArrayUtils.isEmpty(paramValues)) {
                            if (paramValues.length == 1) {
                                paramMap.put(paramName, paramValues[0]);
                            } else {
                                //todo why this situation will occur
                                StringBuilder paramValue = new StringBuilder("");
                                for (int i = 0; i < paramValues.length; i++) {
                                    paramValue.append(paramValues[i]);
                                    if (i != paramValues.length - 1) {
                                        paramValue.append(StringUtils.SEPARATOR);
                                    }
                                }
                                paramMap.put(paramName, paramValue.toString());
                            }
                        }
                    }


                }


            }
        } catch (Exception e) {
            //todo add log
            throw new RuntimeException(e);
        }

        return paramMap;
    }


    /**
     * ignore the cache param of jquery
     *
     * @param paramName
     * @return
     */
    private static boolean checkParamName(String paramName) {
        return !paramName.equals("_");
    }


}
