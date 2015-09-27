package com.slug.core;

import com.slug.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * handler class
 *
 * @author zhw
 * @version 0.1  15/9/25
 */
public class ClassHandler {


    //get base package
    private static final String basePackage = ConfigHandler.getString("com.slug.app.base_package");


    /**
     * get class scanner
     */
    private static final ClassScanner classScanner = ApplicationContext.getClassScanner();


    /**
     * get all class in base package
     *
     * @return
     */
    public static List<Class<?>> getClassList() {
        return classScanner.getClassList(basePackage);
    }

    /**
     * get assgin super class in base package
     *
     * @param superClass
     * @return
     */
    public static List<Class<?>> getClassListBySuper(Class<?> superClass) {

        return classScanner.getClassListBySuper(basePackage, superClass);
    }

    /**
     * get classes with assign annotation in base package
     *
     * @param anntationClass
     * @return
     */
    public static List<Class<?>> getClassListByAnnotation(Class<? extends Annotation> anntationClass) {
        return classScanner.getClassListByAnnotation(basePackage, anntationClass);
    }


}
