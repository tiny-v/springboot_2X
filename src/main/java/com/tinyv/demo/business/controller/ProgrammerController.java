package com.tinyv.demo.business.controller;

import com.tinyv.demo.business.bean.Programmer;
import com.tinyv.demo.business.service.ProgrammerService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author YMa69
 * @date 2019/11/8
 */
@Api()
@RestController
@RequestMapping("/programmer")
public class ProgrammerController extends BaseController{

    private Logger logger = LoggerFactory.getLogger(ProgrammerController.class);

    @Autowired
    private ProgrammerService programmerService;

    @RequestMapping(value="/getProgrammer", method= RequestMethod.GET)
    public Programmer getProgrammer(String nickname){
        logger.info("===== nickname:"+nickname);
        return programmerService.getProgrammer(nickname);
    }

}
