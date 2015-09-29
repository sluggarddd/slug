package com.slug.servlet.bean;

import com.slug.core.BaseBean;
import com.slug.utils.CastUtils;

import java.util.Map;

/**
 * encapsulate request params
 *
 * @author zhw
 * @version 0.1  15/9/29
 */
public class Params extends BaseBean {

    private final Map<String, Object> fieldMap;

    public Params(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    public String getString(String name) {
        return CastUtils.castString(get(name));
    }

    public double getDouble(String name) {
        return CastUtils.castDouble(get(name));
    }

    public long getLong(String name) {
        return CastUtils.castLong(get(name));
    }

    public int getInt(String name) {
        return CastUtils.castInt(get(name));
    }

    private Object get(String name) {
        return fieldMap.get(name);
    }
}
