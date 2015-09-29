package com.slug.servlet;

import com.slug.ApplicationContext;
import com.slug.GlobalConfig;
import com.slug.HttpSessionContext;
import com.slug.servlet.handler.HandlerException;
import com.slug.servlet.handler.HandlerInvoker;
import com.slug.servlet.handler.HandlerMapping;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhw on 15/9/21.
 * <p>
 * core front controller for http request dispatching
 * <p>
 * 1. 获取请求相关信息（请求方法与请求 URL），封装为 RequestBean。
 * 2. 根据 RequestBean 从 Action Map 中获取对应的 ActionBean（包括 Action 类与 Action 方法）。
 * 3. 解析请求 URL 中的占位符，并根据真实的 URL 生成对应的 Action 方法参数列表（Action 方法参数的顺序与 URL 占位符的顺序相同）。
 * 4. 根据反射创建 Action 对象，并调用 Action 方法，最终获取返回值（Result）。
 * 5. 将返回值转换为 JSON 格式（或者 XML 格式，可根据 Action 方法上的 @Response 注解来判断）。
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {


    private HandlerMapping handlerMapping = ApplicationContext.getHandlerMapping();
    private HandlerInvoker handlerInvoker = ApplicationContext.getHandlerInvoker();
    private HandlerException handlerException = ApplicationContext.getHandlerException();


    @Override
    public void init(ServletConfig config) throws ServletException {

//        ServletContext servletContext = config.getServletContext();

    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //set char encoding
        req.setCharacterEncoding(GlobalConfig.CHARACTER_ENCODING);


        //如果handlerMapping没有指定 则返回Index

        try {

            Hamal hamal = handlerMapping.getHamal(req);

            if (hamal == null) {
                //todo return error
                return;
            }

            HttpSessionContext.init(req, resp);

            handlerInvoker.invokeHandler(req, resp, hamal);


        } catch (Exception e) {
            //todo deal with error
            handlerException.handleException(req, resp, e);
        } finally {
            HttpSessionContext.destroy();
        }

    }


}
