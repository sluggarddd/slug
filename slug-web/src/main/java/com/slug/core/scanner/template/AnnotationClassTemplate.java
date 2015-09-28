package com.slug.core.scanner.template;

import java.lang.annotation.Annotation;

/**
 * use to obtain the annotation class
 *
 * @author zhw
 * @version 0.1  15/9/26
 */
public abstract class AnnotationClassTemplate extends ClassTemplate {

    protected final Class<? extends Annotation> annotationClass;

    protected AnnotationClassTemplate(String packageName, Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotationClass = annotationClass;

    }
}
