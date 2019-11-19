package com.tinyv.demo.business.bean;

import java.io.Serializable;

/**
 * Created by YMa69 on 2019/11/8.
 */
public class Programmer implements Serializable{

    public static final Long serialVersionUID = 1L;

    private String nickname;

    private String description;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
