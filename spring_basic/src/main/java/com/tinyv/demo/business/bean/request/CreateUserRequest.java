package com.tinyv.demo.business.bean.request;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
 * @author tiny_v
 * @date 2021/8/6.
 */
@Getter
@Setter
public class CreateUserRequest implements Serializable {

    /** 用户名 */
    @NotNull
    private String username;

    /** 密码 */
    @NotNull
    private String password;

}
