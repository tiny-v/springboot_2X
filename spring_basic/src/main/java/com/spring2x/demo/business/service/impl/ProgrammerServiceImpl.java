package com.spring2x.demo.business.service.impl;

import com.spring2x.core.utils.UUIDUtils;
import com.spring2x.demo.business.bean.Programmer;
import com.spring2x.demo.business.dao.ProgrammerDao;
import com.spring2x.demo.business.service.ProgrammerService;
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

    private static final Logger logger = LoggerFactory.getLogger(ProgrammerServiceImpl.class);

    @Autowired
    private ProgrammerDao programmerDao;

    @Override
    public List<Programmer> listProgrammers(){
        List<Programmer> programmerList = programmerDao.listProgrammers();
        logger.info("programmer list size:[{}]", programmerList.size());
        return programmerList;
    }

    @Override
    public void insertProgrammer(Programmer programmer) {
        programmer.setUuid(UUIDUtils.getUUID32());
        programmerDao.insertProgrammer(programmer);
    }

}
