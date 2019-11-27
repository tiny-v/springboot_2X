package com.tinyv.demo.business.service;

import com.tinyv.demo.business.bean.Programmer;

import java.util.List;

/**
 *
 * @author YMa69
 * @date 2019/11/19
 */
public interface ProgrammerService {

    /**
     * @return
     */
    List<Programmer> listProgrammers();

    void insertProgrammer(Programmer programmer);

}
