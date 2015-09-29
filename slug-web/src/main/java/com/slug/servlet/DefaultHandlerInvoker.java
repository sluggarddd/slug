package com.slug.servlet;

import com.slug.ApplicationContext;
import com.slug.servlet.bean.Params;
import com.slug.servlet.handler.HandlerInvoker;
import com.slug.servlet.render.ViewResolver;
import com.slug.core.bean.BeanFactory;
import com.slug.utils.CastUtils;
import com.slug.utils.ClassUtils;
import com.slug.utils.MapUtils;
import com.slug.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;


/**
 * Handler invoker
 *
 * @author zhw
 * @version 0.1  15/9/28
 */
public class DefaultHandlerInvoker implements HandlerInvoker {

    private ViewResolver viewResolver = ApplicationContext.getViewResolver();

    @Override
    public void invokeHandler(HttpServletRequest request, HttpServletResponse response, Hamal hamal) throws Exception {

        //get the information from controller
        Class<?> mappingClass = hamal.getMappingClass();
        Method mappingMethod = hamal.getMappingMethod();

        //create controller
        Object controllerInstance = BeanFactory.getBean(mappingClass);

        //create action param List
        List<Object> paramList = createParamListInMethod(request, hamal);

        //check the params is illegal
        validateParams(mappingMethod, paramList);

        //invoke method that matches
        Object result = invokeMappingMethod(mappingMethod, controllerInstance, paramList);

        viewResolver.resolveView(request, response, result);

    }


    private List<Object> createParamListInMethod(HttpServletRequest request, Hamal hamal) throws Exception {
        //define param list
        List<Object> paramList = new ArrayList<Object>();

        //get the method's params' type
        Class<?>[] paramTypes = hamal.getMappingMethod().getParameterTypes();

        //add the param in path
        paramList.addAll(createParamInPath(hamal.getMatcher(), paramTypes));

        //todo deal with upload file

        //include form data and query String
        Map<String, Object> requestParamMap = WebUtils.getRequestParamMap(request);

        if (!MapUtils.isEmpty(requestParamMap)) {
            paramList.add(new Params(requestParamMap));
        }

        //return paramList
        return paramList;
    }


    private List<Object> createParamInPath(Matcher requestPathMatcher, Class<?>[] paramTypes) {

        List<Object> paramList = new ArrayList<Object>();

        for (int i = 1; i <= requestPathMatcher.groupCount(); i++) {

            //get param
            String param = requestPathMatcher.group(i);
            //get type of param
            Class<?> paramType = paramTypes[i - 1];

            if (ClassUtils.isInt(paramType)) {
                paramList.add(CastUtils.castInt(param));
            } else if (ClassUtils.isLong(paramType)) {
                paramList.add(CastUtils.castLong(param));
            } else if (ClassUtils.isDouble(paramType)) {
                paramList.add(CastUtils.castDouble(param));
            } else if (ClassUtils.isString(paramType)) {
                paramList.add(param);
            }

        }
        //返回参数列表
        return paramList;
    }


    private void validateParams(Method mappingMethod, List<Object> mappingMethodParams) {
        //judge the number of params is matching
        Class<?>[] mappingMethodParamTypes = mappingMethod.getParameterTypes();
        if (mappingMethodParamTypes.length != mappingMethodParams.size()) {
            String message = String.format("params num not match need %d but get $d",
                    mappingMethodParamTypes.length, mappingMethodParams.size());
            throw new RuntimeException(message);
        }
    }


    private Object invokeMappingMethod(Method mappingMethod, Object mappingInstance, List<Object> paramList) throws Exception {
        // invoke mapping method
        mappingMethod.setAccessible(true);//cancel the security test (improve the efficiency)
        return mappingMethod.invoke(mappingInstance, paramList.toArray());
    }
}
