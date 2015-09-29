package com.slug.core;

import java.io.Serializable;

/**
 * @author zhw
 * @version 0.1  15/9/29
 */
public abstract class BaseBean implements Serializable {


    //todo implement these function

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
