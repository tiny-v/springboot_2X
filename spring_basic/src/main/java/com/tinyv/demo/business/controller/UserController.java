package com.tinyv.demo.business.controller;

import com.tinyv.demo.business.bean.User;
import com.tinyv.demo.business.service.UserService;
import com.tinyv.demo.global.constant.ConstResponseCode;
import com.tinyv.demo.global.response.CommonResponse;
import com.tinyv.demo.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author TinyV
 * @date 2019/12/10
 */
@RestController(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/register")
    public CommonResponse register(String username, String password){
        CommonResponse commonResponse = new CommonResponse();
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            commonResponse.setMsg("用户名或密码不能为空");
            commonResponse.setErrorCode(ConstResponseCode.RESPONSE_CODE_REGISTER_ILLEGAL_INFO);
            return commonResponse;
        }
        if(userService.getUser(username)!=null){
            commonResponse.setMsg("该用户名已存在");
            commonResponse.setErrorCode(ConstResponseCode.RESPONSE_CODE_REGISTER_USER_EXIST);
            return commonResponse;
        }
        User user = new User(UUIDUtils.getUUID32(), username, password);
        userService.register(user);
        commonResponse.setMsg("注册成功");
        commonResponse.setData(user);
        return commonResponse;
    }

}
