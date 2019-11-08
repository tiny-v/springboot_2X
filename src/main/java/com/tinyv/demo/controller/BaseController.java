package com.tinyv.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YMa69 on 2019/11/8.
 */
@RestController
@RequestMapping("/base/")
public class BaseController {

    @RequestMapping("/query")
    public String controllerDemo(org.apache.http.HttpRequest httpRequest){
        System.out.println("reach to me");
        return "hello world";
    }

}
