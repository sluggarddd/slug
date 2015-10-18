package com.slug.ioc;

import com.slug.core.exception.InitializationError;
import com.slug.ioc.annotation.Resource;
import com.slug.utils.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * the ioc container
 *
 * @author zhw
 * @version 0.1  15/10/6
 */
public class IOCAdaptor {

    static {

        try {

            // get AllBean
            Map<Class<?>, Object> beanMap = BeanAdaptor.getBeanMap();

            for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {

                //get bean class and bean instance
                Class<?> clasz = entry.getKey();
                Object instance = entry.getValue();

                //get all the field in bean class (not include the method in super)
                Field[] fields = clasz.getDeclaredFields();
                if (!ArrayUtils.isEmpty(fields)) {
                    //traverse bean
                    for (Field field : fields) {
                        //if the field contain resource annotation
                        if (field.isAnnotationPresent(Resource.class)) {

                            //if the field contain resource annotation that must be an interface
                            Class<?> ifClass = field.getType();
                            //get the implements of interface class
                            Object implInstance = findImplInstance(ifClass);

                            //if find the implClass
                            if (implInstance != null) {
                                //set the field
                                field.setAccessible(true);//set the field public
                                field.set(instance, implInstance);
                            } else {
                                throw new InitializationError("ioc fail in class " + clasz.getName() + "in field " + ifClass.getName());
                            }

                        }
                    }

                }

            }
        } catch (Exception e) {
            //todo add log
            throw new InitializationError("Init IocHelper error！", e);
        }


    }


    private static Object findImplInstance(Class<?> ifClass) {
        //it must be declare in service Annotation

        return BeanAdaptor.getBeanMap().get(ifClass);
    }


}
