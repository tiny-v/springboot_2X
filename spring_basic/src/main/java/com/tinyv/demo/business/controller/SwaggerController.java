package com.tinyv.demo.business.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TinyV
 * @date 2019/11/19
 */
@Api(tags={"RestAPI Demo"}, produces = "application/json")
@RestController
@RequestMapping(value="/swagger")
public class SwaggerController {

    @ApiOperation(value="(RestAPI DEMO)", notes="GET方法的Demo")
    @ApiImplicitParams(value={@ApiImplicitParam(name="pluginName", value = "插件名称"),
                              @ApiImplicitParam(name="pluginVersion", value="插件版本")})
    @GetMapping(value="/swaggerGet")
    public String swaggerGet(String pluginName, String pluginVersion){
        return pluginName+":"+pluginVersion;
    }

}
