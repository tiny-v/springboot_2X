package com.spring2x.demo.business.controller;

import com.spring2x.demo.business.bean.request.TestRequestTypeA;
import com.spring2x.demo.business.bean.request.TestRequestTypeB;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(TestRequestTypeController.class) ;

    /**
     * @param testRequest
     * @return
     */
    @PostMapping(value="/testParam1")
    public void testParam1(@RequestBody TestRequestTypeA testRequest){
        logger.info("testRequest.getA:[{}], testRequest.getA.getClass:[{}]", testRequest.getA(), testRequest.getA().getClass());
        logger.info("testRequest.getB:[{}], testRequest.getA.getClass:[{}]", testRequest.getB(), testRequest.getA().getClass());
        logger.info("testRequest.getC:[{}], testRequest.getA.getClass:[{}]", testRequest.getC(), testRequest.getA().getClass());
    }

    /**
     * @param testRequest
     * @return
     */
    @PostMapping(value="/testParam2")
    public void testParam2(@RequestBody TestRequestTypeB testRequest){
        logger.info("testRequest.getA:[{}], testRequest.getA.getClass:[{}]", testRequest.getA(), testRequest.getA().getClass());
        logger.info("testRequest.getB:[{}], testRequest.getA.getClass:[{}]", testRequest.getB(), testRequest.getA().getClass());
        logger.info("testRequest.getC:[{}], testRequest.getA.getClass:[{}]", testRequest.getC(), testRequest.getA().getClass());
    }

}
