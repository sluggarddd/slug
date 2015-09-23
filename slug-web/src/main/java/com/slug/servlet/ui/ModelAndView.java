package com.slug.servlet.ui;

import java.util.Map;

/**
 * @author zhw
 * @version 0.1  15/9/22
 */
public class ModelAndView {


    private Object view;

    private ModelMap model;


    public ModelAndView() {

    }

    public void setViewName(String viewName) {
        this.view = viewName;
    }

    public boolean hasView() {
        return (this.view != null);
    }

    public ModelAndView add(String key, Object value) {
        getModelMap().add(key, value);
        return this;
    }

    public ModelMap getModelMap() {
        if (this.model == null) {
            this.model = new ModelMap();
        }
        return this.model;
    }

}
