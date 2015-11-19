package com.slug.demo.service.impl;

import com.slug.dao.SqlAdaptor;
import com.slug.demo.pojo.UserBean;
import com.slug.demo.service.TestService;
import com.slug.orm.DataSet;

import java.util.List;

/**
 * Created by sluggarddd on 2015/10/16.
 */
public class TestServiceImpl implements TestService {

    private static final String LIST_USER_SQL = SqlAdaptor.getSql("LIST_USER");

    @Override
    public void test() throws Exception {
        List<UserBean> dataList = DataSet.selectListWithCondition(UserBean.class, null, null);

        System.out.println(dataList.size());
        for (UserBean userBean : dataList) {
            System.out.println(userBean.getUsername());
        }
    }
}
