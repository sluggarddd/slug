package com.slug.servlet.adapter;

import com.slug.core.ClassHandler;
import com.slug.servlet.Hamal;
import com.slug.servlet.Requester;
import com.slug.servlet.annotation.Controller;
import com.slug.servlet.annotation.RequestMapping;
import com.slug.servlet.handler.HandlerMapping;
import com.slug.utils.ArrayUtils;
import com.slug.utils.ClassUtils;
import com.slug.utils.CollectionUtils;
import com.slug.utils.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @version 0.1  15/9/25
 */
public class URIAdaptor {


    /**
     * http request and method
     */
    private static final Map<Requester, Hamal> controllerMap = new LinkedHashMap<Requester, Hamal>();


    static {

        List<Class<?>> controllerClassList = ClassHandler.getClassListByAnnotation(Controller.class);


        //get the action now
        if (!CollectionUtils.isEmpty(controllerClassList)) {

            //define action map
            //common url controller
            Map<Requester, Hamal> commonControllers = new HashMap<Requester, Hamal>();
            Map<Requester, Hamal> regexpControllers = new HashMap<Requester, Hamal>();

            //traverse controllers
            for (Class<?> actionClass : controllerClassList) {

                Method[] controllerMethods = actionClass.getDeclaredMethods();
                if (!ArrayUtils.isEmpty(controllerMethods)) {
                    for (Method method : controllerMethods) {
                        //deal with the annotation
                        handleControllerMethod(actionClass, method, commonControllers, regexpControllers);

                    }
                }


            }


            //put the common mapping before the regex mapping
            controllerMap.putAll(commonControllers);
            controllerMap.putAll(regexpControllers);

        }

    }


    private static void handleControllerMethod(Class<?> controllerClass, Method controllerMethod,
                                               Map<Requester, Hamal> commonControllers,
                                               Map<Requester, Hamal> regexControllers) {
        // judge if this method contain annotation
        if (controllerMethod.isAnnotationPresent(RequestMapping.GET.class)) {
            String requestPath = controllerMethod.getAnnotation(RequestMapping.GET.class).value();
            registerMapping("GET", requestPath, controllerClass, controllerMethod, commonControllers, regexControllers);
        } else if (controllerMethod.isAnnotationPresent(RequestMapping.POST.class)) {
            String requestPath = controllerMethod.getAnnotation(RequestMapping.GET.class).value();
            registerMapping("POST", requestPath, controllerClass, controllerMethod, commonControllers, regexControllers);
        } else if (controllerMethod.isAnnotationPresent(RequestMapping.POST.class)) {
            String requestPath = controllerMethod.getAnnotation(RequestMapping.GET.class).value();
            registerMapping("PUT", requestPath, controllerClass, controllerMethod, commonControllers, regexControllers);
        } else if (controllerMethod.isAnnotationPresent(RequestMapping.POST.class)) {
            String requestPath = controllerMethod.getAnnotation(RequestMapping.GET.class).value();
            registerMapping("DELETE", requestPath, controllerClass, controllerMethod, commonControllers, regexControllers);
        }

    }


    private static void registerMapping(String requestMethod, String requestPath,
                                        Class<?> controllerClass,
                                        Method actionMethod,
                                        Map<Requester, Hamal> commonControllers,
                                        Map<Requester, Hamal> regexControllers) {

        //jude if the request path contain placeholder
        if (requestPath.matches(".+\\{\\w+\\}.*")) {

            // 将请求路径中的占位符 {\w+} 转换为正则表达式 (\\w+)
            requestPath = StringUtils.replaceAll(requestPath, "\\{\\w+\\}", "(\\\\w+)");
            // 将 Requester 与 Handler 放入 Regexp Action Map 中
            regexControllers.put(new Requester(requestMethod, requestPath),
                    new Hamal(controllerClass, actionMethod));

        } else {

            commonControllers.put(new Requester(requestMethod, requestPath),
                    new Hamal(controllerClass, actionMethod));
        }

    }


    /**
     * get controller map
     *
     * @return
     */
    public static Map<Requester, Hamal> getControllerMap() {
        return controllerMap;
    }

}
