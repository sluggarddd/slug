package com.slug.demo.service;

import com.slug.demo.service.impl.TestServiceImpl;
import com.slug.ioc.annotation.Inject;

/**
 * Created by sluggarddd on 2015/10/16.
 */
@Inject(TestServiceImpl.class)
public interface TestService {

    public void test()throws Exception;
}
