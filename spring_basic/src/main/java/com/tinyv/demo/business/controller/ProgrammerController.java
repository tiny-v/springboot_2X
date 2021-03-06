package com.tinyv.demo.business.controller;

import com.tinyv.demo.business.bean.Programmer;
import com.tinyv.demo.business.service.ProgrammerService;
import com.tinyv.demo.global.response.CommonResponse;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author TinyV
 * @date 2019/11/8
 */
@Api()
@RestController
@RequestMapping("/programmer")
public class ProgrammerController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(ProgrammerController.class);

    @Autowired
    private ProgrammerService programmerService;

    @RequestMapping(value="/listProgrammers", method= RequestMethod.GET)
    public CommonResponse listProgrammer(){
        List<Programmer> list = programmerService.listProgrammers();
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setData(list);
        commonResponse.setMsg("");
        return commonResponse;
    }

    @RequestMapping(value="/insertProgrammer", method= RequestMethod.POST)
    public void insertProgrammer(Programmer programmer){
        programmerService.insertProgrammer(programmer);
    }

}
