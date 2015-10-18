package com.slug.ioc;

import com.slug.core.ClassHandler;
import com.slug.core.exception.InitializationError;
import com.slug.ioc.annotation.Inject;
import com.slug.servlet.annotation.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * adaptor for bean in order to init bean
 * <p>
 * when the bean has the annotation inject
 * means that it need to inject a implement
 *
 * @author zhw
 * @version 0.1  15/10/4
 */
public class BeanAdaptor {

    /**
     * container of bean
     */
    private static final Map<Class<?>, Object> beanMap = new HashMap<Class<?>, Object>();

    static {
        try {
            //get all class
            List<Class<?>> classList = ClassHandler.getClassList();
            for (Class<?> cls : classList) {
                // get the class with assigned annotation
                if (cls.isAnnotationPresent(Inject.class) ||
                        cls.isAnnotationPresent(Controller.class)) {

                    Object instance = null;
                    try {
                        // build bean
                        if (cls.isAnnotationPresent(Inject.class)) {
                            instance = cls.getAnnotation(Inject.class).value().newInstance();
                        } else {
                            instance = cls.newInstance();
                        }


                    } catch (Exception e) {
                        throw new InitializationError("class " + cls.toString() + " is unable to init", e);
                    }


                    // put class in Bean Map
                    beanMap.put(cls, instance);

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


}
