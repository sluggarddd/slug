package com.slug.core.scanner;

import com.slug.core.ClassScanner;
import com.slug.utils.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author zhw
 * @version 0.1  15/9/25
 */
public class DefaultClassScanner implements ClassScanner {


    @Override
    public List<Class<?>> getClassList(String packageName) {

        return new ClassTemplate(packageName) {

            @Override
            public boolean checkAddClass(Class<?> cls) {
                String className = cls.getName();
                String pkgName = className.substring(0, className.lastIndexOf("."));

                return pkgName.startsWith(packageName);
            }
        }.getClassList();

    }

    @Override
    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return null;
    }

    @Override
    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(DefaultClassScanner.class.getName());
    }
}
