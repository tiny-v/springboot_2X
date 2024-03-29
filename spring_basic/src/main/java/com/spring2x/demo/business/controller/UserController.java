package com.spring2x.demo.business.controller;

import com.spring2x.core.response.BaseResponse;
import com.spring2x.demo.business.bean.request.CreateUserRequest;
import com.spring2x.demo.business.bean.User;
import com.spring2x.demo.business.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author TinyV
 * @date 2019/12/10
 */
@Controller(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @param createUserModel
     * @return
     */
    @PostMapping(value="/register")
    public void register(@RequestBody CreateUserRequest createUserModel){
        User user = User.builder()
                .username(createUserModel.getUsername())
                .password(createUserModel.getPassword())
                .description(createUserModel.getDescription())
                .nickname(createUserModel.getNickname())
                .telephone(createUserModel.getTelephone())
                .email(createUserModel.getEmail())
                .build();
        userService.insertUser(user);
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping(value="/getUser")
    @ResponseBody
    public BaseResponse<User> getUser(@RequestParam String userId){
        BaseResponse.BaseResponseBuilder<User> builder = BaseResponse.builder();
        if(StringUtils.isBlank(userId)){
            return builder.build();
        }
        User user = userService.getUserById(userId);
        return builder.data(user).build();
    }

}
