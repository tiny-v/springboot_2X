package com.spring2x.demo.business.dao;

import com.spring2x.demo.business.bean.Programmer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author TinyV
 * @date 2019/11/22
 */
@Repository
public interface ProgrammerDao {

    /**
     * 插入数据
     * @param programmer
     */
    int insertProgrammer(Programmer programmer);

    /**
     * 查询所有的 programmer
     * @return
     */
    List<Programmer> listProgrammers();

}
