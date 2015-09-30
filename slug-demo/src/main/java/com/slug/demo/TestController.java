package com.slug.demo;

import com.slug.servlet.annotation.Controller;
import com.slug.servlet.annotation.RequestMapping;
import com.slug.servlet.render.Result;

/**
 * @author zhw
 * @version 0.1  15/9/30
 */
@Controller
public class TestController {

    @RequestMapping.GET("/hello")
    public Result get() throws Exception {

        return new Result(true);
    }
}
