package com.slug.core.scanner;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author zhw
 * @version 0.1  15/9/25
 */
public interface ClassScanner {

    /**
     * get all the class in package
     *
     * @param packageName
     * @return
     */
    List<Class<?>> getClassList(String packageName);


    /**
     * obtain the class with assign annotation
     *
     * @param packageName
     * @param annotationClass
     * @return
     */
    List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);


    /**
     * obtain the classes which in  package and have assign super class
     *
     * @param packageName
     * @param superClass
     * @return
     */
    List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass);

}
