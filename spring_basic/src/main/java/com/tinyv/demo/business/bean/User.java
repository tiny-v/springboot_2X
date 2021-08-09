package com.tinyv.demo.business.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * 用户类
 * @author TinyV
 * @date 2019/12/10
 */
@Getter
@Setter
@Builder
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

}
