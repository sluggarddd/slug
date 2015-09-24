package com.slug.servlet;

import java.lang.reflect.Method;
import java.util.regex.Matcher;

/**
 * 封装分发的相关信息，在handler中传递
 *
 * @author zhw
 * @version 0.1  15/9/24
 */

public class Hamal {

    private Class<?> mappingClass;
    private Method mappingMethod;
    private Matcher matcher;

    public Hamal(Class<?> mappingClass, Method mappingMethod) {
        this.mappingClass = mappingClass;
        this.mappingMethod = mappingMethod;
    }

    public Class<?> getMappingClass() {
        return mappingClass;
    }

    public Method getMappingMethod() {
        return mappingMethod;
    }


    public Matcher getMatcher() {
        return matcher;
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }
}
