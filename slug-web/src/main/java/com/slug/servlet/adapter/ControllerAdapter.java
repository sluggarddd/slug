package com.slug.servlet.adapter;

import com.slug.core.ClassHandler;
import com.slug.servlet.annotation.Controller;
import com.slug.core.exception.InitializationError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 * @version 0.1  15/9/29
 */
public class ControllerAdapter {


    private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

    static {
        try {

            //get all class in application package
            List<Class<?>> classList = ClassHandler.getClassList();
            for (Class<?> cls : classList) {
                //if class contain our annotation
                if (cls.isAnnotationPresent(Controller.class)) {
                    //build Bean instance
                    Object beanInstance = cls.newInstance();

                    beanMap.put(cls, beanInstance);

                }
            }
        } catch (Exception e) {
            throw new InitializationError("init beanFactory error", e);

        }
    }

    /**
     * get beanMap
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    /**
     * get bean instance
     *
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls) {
        if (!beanMap.containsKey(cls)) {
            throw new RuntimeException("CLASS NOT FOUND " + cls);
        }
        return (T) beanMap.get(cls);
    }

    /**
     * set bean instance
     *
     * @param cls
     * @param obj
     */
    public static void setBean(Class<?> cls, Object obj) {
        beanMap.put(cls, obj);
    }
}
