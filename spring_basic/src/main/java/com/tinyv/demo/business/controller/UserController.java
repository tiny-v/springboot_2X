package com.tinyv.demo.business.controller;

import com.tinyv.demo.business.bean.User;
import com.tinyv.demo.business.bean.request.CreateUserRequest;
import com.tinyv.demo.business.service.UserService;
import com.tinyv.demo.global.response.BaseResponse;
import com.tinyv.demo.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author TinyV
 * @date 2019/12/10
 */
@Controller(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/register", method= RequestMethod.POST)
    @ResponseBody
    public BaseResponse register(@RequestBody CreateUserRequest createUserModel){
        User user = User.builder()
                .username(createUserModel.getUsername())
                .password(createUserModel.getPassword())
                .UUID(UUIDUtils.getUUID32())
                .build();
        userService.register(user);
        return BaseResponse.builder().build();
    }

}
