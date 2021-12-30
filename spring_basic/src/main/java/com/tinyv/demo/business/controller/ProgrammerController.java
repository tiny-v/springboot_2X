package com.tinyv.demo.business.controller;

import com.tinyv.demo.business.bean.Programmer;
import com.tinyv.demo.business.service.ProgrammerService;
import com.tinyv.demo.global.response.BaseResponse;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author TinyV
 * @date 2019/11/8
 */
@Api()
@RestController
@RequestMapping("/programmer")
public class ProgrammerController {

    private Logger logger = LoggerFactory.getLogger(ProgrammerController.class);

    @Autowired
    private ProgrammerService programmerService;

    @GetMapping(value="/listProgrammers")
    public BaseResponse<List<Programmer>> listProgrammer(){
        logger.info("== list programmer ==");
        List<Programmer> list = programmerService.listProgrammers();
        BaseResponse.BaseResponseBuilder<List<Programmer>> builder = BaseResponse.builder();
        return builder.data(list).build();
    }

    @PostMapping(value="/insertProgrammer")
    public void insertProgrammer(Programmer programmer){
        programmerService.insertProgrammer(programmer);
    }

}
