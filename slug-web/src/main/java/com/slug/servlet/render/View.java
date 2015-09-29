package com.slug.servlet.render;

import com.slug.core.BaseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * view object
 *
 * @author zhw
 * @version 0.1  15/9/29
 */
public class View extends BaseBean {

    private String path;//view path

    private Map<String, Object> data;//data in view

    public View(String path) {
        this.path = path;
        data = new HashMap<String, Object>();
    }

    public View(String path, Map<String, Object> data) {
        this.path = path;
        this.data = data;
    }

    public View data(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public boolean isRedirect() {
        return path.startsWith("/");
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }


}
