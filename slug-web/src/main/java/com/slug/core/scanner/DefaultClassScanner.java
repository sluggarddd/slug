package com.slug.core.scanner;

import com.slug.core.ClassScanner;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author zhw
 * @version 0.1  15/9/25
 */
public class DefaultClassScanner implements ClassScanner {


    public List<Class<?>> getClassList(String packageName) {
        return null;
    }

    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return null;
    }

    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return null;
    }
}
