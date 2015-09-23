package com.slug.servlet.ui;

import com.slug.utils.Assert;

import java.util.LinkedHashMap;

/**
 * @author zhw
 * @version 0.1  15/9/23
 */
public class ModelMap extends LinkedHashMap<String, Object> {


    public ModelMap() {

    }

    public ModelMap add(String key, Object value) {
        Assert.isNull(key, "key must not be null");
        put(key, value);
        return this;
    }

}
