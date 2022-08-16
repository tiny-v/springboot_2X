package com.spring2x.demo.business.controller;

import com.spring2x.core.response.BaseResponse;
import com.spring2x.demo.business.bean.Programmer;
import com.spring2x.demo.business.service.ProgrammerService;
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
