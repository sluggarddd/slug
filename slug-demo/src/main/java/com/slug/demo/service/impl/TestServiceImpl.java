package com.slug.demo.service.impl;

import com.slug.demo.service.TestService;

/**
 * Created by sluggarddd on 2015/10/16.
 */
public class TestServiceImpl implements TestService {
    @Override
    public void test() throws Exception {
        System.out.println("*************IOC TEST***********************");
    }
}
