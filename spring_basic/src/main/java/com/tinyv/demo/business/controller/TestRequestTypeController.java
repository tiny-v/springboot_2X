package com.tinyv.demo.business.controller;

import com.tinyv.demo.business.bean.request.TestRequestTypeA;
import com.tinyv.demo.business.bean.request.TestRequestTypeB;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author tiny_v
 * @date 2021/8/25.
 *
 *
 * 测试框架的反序列化， 发现RequestBody请求的参数类型， 没有区分String int boolean， 可以相互转化
 */
@Api(tags={"Request Demo"}, produces = "application/json")
@RestController
@RequestMapping(value="/test")
public class TestRequestTypeController {


    /**
     * @param testRequest
     * @return
     */
    @RequestMapping(value="/testParam1", method= RequestMethod.POST)
    @ResponseBody
    public void testParam1(@RequestBody TestRequestTypeA testRequest){
        System.out.println(testRequest.getA() + "," + testRequest.getA().getClass());
        System.out.println(testRequest.getB() + "," + testRequest.getA().getClass());
        System.out.println(testRequest.getC() + "," + testRequest.getA().getClass());
    }

    /**
     * @param testRequest
     * @return
     */
    @RequestMapping(value="/testParam2", method= RequestMethod.POST)
    @ResponseBody
    public void testParam2(@RequestBody TestRequestTypeB testRequest){
        System.out.println(testRequest.getA() + "," + testRequest.getA().getClass());
        System.out.println(testRequest.getB() + "," + testRequest.getA().getClass());
        System.out.println(testRequest.getC() + "," + testRequest.getA().getClass());
    }

}
