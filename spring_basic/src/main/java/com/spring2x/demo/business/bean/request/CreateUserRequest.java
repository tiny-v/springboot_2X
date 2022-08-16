package com.spring2x.demo.business.bean.request;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author tiny_v
 * @date 2021/8/6.
 */
@Getter
@Setter
public class CreateUserRequest implements Serializable {

    /** 用户名 */
    @NotBlank
    private String username;

    /** 密码 */
    @NotBlank
    private String password;

    /** 昵称 */
    private String nickname;

    /** 描述 */
    private String description;

    /** 邮箱 */
    private String email;

    /** 类型 **/
    private String type;

    /** 电话 **/
    private String telephone;


}
