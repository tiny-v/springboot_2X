package com.spring2x.demo.business.service;

import com.spring2x.demo.business.bean.Programmer;

import java.util.List;

/**
 *
 * @author TinyV
 * @date 2019/11/19
 */
public interface ProgrammerService {

    /**
     * 查询所有的programmer
     * @return
     */
    List<Programmer> listProgrammers();

    /**
     * 插入Programmer
     * @param programmer
     */
    void insertProgrammer(Programmer programmer);

}
