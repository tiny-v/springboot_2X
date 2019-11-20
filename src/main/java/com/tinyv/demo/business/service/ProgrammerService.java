package com.tinyv.demo.business.service;

import com.tinyv.demo.business.bean.Programmer;

/**
 *
 * @author YMa69
 * @date 2019/11/19
 */
public interface ProgrammerService {

    /**
     * get programmer by nickname
     * @param nickname
     * @return
     */
    Programmer getProgrammer(String nickname);

}
