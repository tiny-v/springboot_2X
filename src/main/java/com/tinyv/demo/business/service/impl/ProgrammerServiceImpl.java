package com.tinyv.demo.business.service.impl;

import com.tinyv.demo.business.bean.Programmer;
import com.tinyv.demo.business.service.ProgrammerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author YMa69
 * @date 2019/11/19
 */
@Service
public class ProgrammerServiceImpl implements ProgrammerService{

    private Logger logger = LoggerFactory.getLogger(ProgrammerService.class);

    @Override
    @Cacheable(value = "programmer", key="#nickname")
    public Programmer getProgrammer(String nickname){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        String id = String.valueOf(System.currentTimeMillis());
        Programmer programmer = new Programmer();
        programmer.setNickname(nickname);
        programmer.setUUID(id);
        return programmer;
    }

}
