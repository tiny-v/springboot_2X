package com.tinyv.demo.business.service.impl;

import com.tinyv.demo.business.bean.Programmer;
import com.tinyv.demo.business.dao.ProgrammerDao;
import com.tinyv.demo.business.service.ProgrammerService;
import com.tinyv.demo.global.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * @author TinyV
 * @date 2019/11/19
 */
@Service
public class ProgrammerServiceImpl implements ProgrammerService{

    private Logger logger = LoggerFactory.getLogger(ProgrammerService.class);

    @Autowired
    private ProgrammerDao programmerDao;

    @Override
    public List<Programmer> listProgrammers(){
        List<Programmer> programmerList = programmerDao.listProgrammers();
        logger.info(""+programmerList.size());
        return programmerList;
    }

    @Override
    public void insertProgrammer(Programmer programmer) {
        programmer.setUUID(UUIDUtils.getUUID32());
        programmerDao.insertProgrammer(programmer);
    }

}
