package com.slug.ioc;

import com.slug.core.ClassHandler;
import com.slug.core.exception.InitializationError;
import com.slug.ioc.annotation.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * adaptor for bean in order to init bean
 *
 * @author zhw
 * @version 0.1  15/10/4
 */
public class BeanAdaptor {

    /**
     * container of bean
     */
    private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

    private static final Map<String, Object> serviceMap = new HashMap<String, Object>();

    static {
        try {
            //get all class
            List<Class<?>> classList = ClassHandler.getClassList();
            for (Class<?> cls : classList) {
                // get the class with assigned annotation
                if (cls.isAnnotationPresent(Service.class)) {

                    // build bean
                    Object instance = cls.newInstance();

                    // put class in Bean Map
                    beanMap.put(cls, instance);


                    //todo is necessary to use Bean<Class<?>,Object>instead of  Map<String, Object> serviceMap

                    //todo when the interface of service has the same simple name it will fail to implement
                    //the front will be override by the following class with the same key
                    serviceMap.put(cls.getAnnotation(Service.class).value().toLowerCase(), instance);
                }
            }
        } catch (Exception e) {
            throw new InitializationError("error when init BeanAdaptor", e);
        }
    }


    /**
     * get bean map
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }

    /**
     * get the instance of bean
     *
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls) {

        if (!beanMap.containsKey(cls)) {
            throw new RuntimeException("can not get instance of class " + cls);
        }
        return (T) beanMap.get(cls);
    }


    /**
     * add an instance to beanMAp
     *
     * @param cls
     * @param object
     */
    public static void setBean(Class<?> cls, Object object) {
        beanMap.put(cls, object);
    }


    public static <T> T getBean(String key) {

        if (!serviceMap.containsKey(key)) {
            throw new RuntimeException(" can not get instance of class" + key);
        }
        return (T) serviceMap.get(key);
    }
}
