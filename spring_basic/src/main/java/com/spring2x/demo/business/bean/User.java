package com.spring2x.demo.business.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户类
 * @author TinyV
 * @date 2019/12/10
 */
@Getter
@Setter
@Builder
@Data
public class User implements Serializable{

    /** 类型 */
    public enum UserType{
        SINGER, PROGRAMMER, DREAMER
    }

    private String id;
    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 昵称 */
    private String nickname;
    /** 描述 */
    private String description;
    /** 邮箱 */
    private String email;
    /** 电话 **/
    private String telephone;
    /** 类型 **/
    private UserType type;
    /** 是否启用 **/
    private Boolean isEnabled;
    /** 是否被删除 **/
    private Boolean isDeleted;
    /** 创建时间 **/
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /** 更新时间 **/
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
