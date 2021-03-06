package com.tinyv.demo.business.bean;

import java.io.Serializable;

/**
 *
 * @author TinyV
 * @date 2019/11/8
 */
public class Programmer implements Serializable{

    public static final Long serialVersionUID = 1L;

    private String UUID;

    private int num;

    private String nickname;

    private String description;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

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
