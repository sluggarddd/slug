package com.slug.demo.pojo;

import com.slug.orm.annotation.Entity;
import com.slug.orm.annotation.Table;

/**
 * @author zhw
 * @version 0.1  15/11/18
 */
@Entity
@Table("hehe_user")
public class UserBean {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
