package com.tinyv.demo.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YMa69
 * @date 2019/11/19
 */
@Api(tags={"RestApi 接口规范展示"}, description = " 使用SwaggerController展示常用的RestApi规范", produces = "application/json")
@RestController
@RequestMapping(value="/swagger")
public class SwaggerController {

    @ApiOperation(value="(GET DEMO)", notes="GET方法的emo")
    @ApiImplicitParams(value={@ApiImplicitParam(name="pluginName", value = "插件名称"),
                              @ApiImplicitParam(name="pluginVersion", value="插件版本")})
    @RequestMapping(value="/swaggerGet", method= RequestMethod.GET)
    public String swaggerGet(String pluginName, String pluginVersion){
        return pluginName+":"+pluginVersion;
    }

}
