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
