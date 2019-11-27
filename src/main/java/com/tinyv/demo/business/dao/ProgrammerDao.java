package com.tinyv.demo.business.dao;

import com.tinyv.demo.business.bean.Programmer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @author YMa69
 * @date 2019/11/22
 */
public interface ProgrammerDao {

    /**
     * 插入数据
     * @param programmer
     */
    void insertProgrammer(Programmer programmer);

    /**
     * @return
     */
    List<Programmer> listProgrammers();

}
