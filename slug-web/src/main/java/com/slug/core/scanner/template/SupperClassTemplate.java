package com.slug.core.scanner.template;

/**
 * @author zhw
 * @version 0.1  15/9/28
 */
public abstract class SupperClassTemplate extends ClassTemplate {

    protected final Class<?> superClass;

    protected SupperClassTemplate(String packageName, Class<?> superClass) {
        super(packageName);
        this.superClass = superClass;

    }

}
