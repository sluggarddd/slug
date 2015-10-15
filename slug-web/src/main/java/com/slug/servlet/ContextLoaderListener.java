package com.slug.servlet;

import com.slug.AdaptorContainer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * default context listener do something initial
 *
 * @author zhw
 * @version 0.1  15/10/4
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {


        ServletContext servletContext = sce.getServletContext();

        //init adaptor
        AdaptorContainer.init();
        //register plugins


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {


        //destroy plugins


    }


    private void destroyPlugins() {
//        List<Plugin>
    }
}
