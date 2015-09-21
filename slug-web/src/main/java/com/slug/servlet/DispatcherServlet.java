package com.slug.servlet;

import com.slug.servlet.handler.Handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhw on 15/9/21.
 * <p>
 * core front controller for http request dispatching
 */
public class DispatcherServlet extends HttpServlet {


//    private List<Handler> sysHandlers = new ArrayList<Handler>();


    @Override
    public void init() throws ServletException {


    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }


}
