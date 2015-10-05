package com.slug.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * default context listener do something initial
 *
 * @author zhw
 * @version 0.1  15/10/4
 */
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {


        ServletContext servletContext = sce.getServletContext();


        //register plugins

        //hehehe

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {


        //destroy plugins


    }


    private void destroyPlugins() {
//        List<Plugin>
    }
}
