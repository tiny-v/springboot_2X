package com.tinyv.demo.business.bean;

import java.io.Serializable;

/**
 * 用户类
 * @author TinyV
 * @date 2019/12/10
 */
public class User implements Serializable{

    private String UUID;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;

    public User(String UUID, String username, String password){
        this.UUID = UUID;
        this.username = username;
        this.password = password;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

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
