package com.spring2x.demo.test.tasks;

import com.spring2x.demo.business.bean.Programmer;
import com.spring2x.demo.business.service.ProgrammerService;

import java.util.concurrent.Callable;

/**
 * Created by TinyV on 2019/11/26.
 */
public class MySqlTestTask implements Callable {

    private ProgrammerService programmerService;
    private Programmer programmer;
    private String methodName;

    public MySqlTestTask(ProgrammerService programmerService, String methodName){
        this.programmerService = programmerService;
        this.methodName = methodName;
    }

    public MySqlTestTask(ProgrammerService programmerService, String methodName, Programmer programmer){
        this.programmerService = programmerService;
        this.methodName = methodName;
        this.programmer = programmer;
    }

    @Override
    public Object call() throws Exception {
        if(methodName.equals("INSERT")){
            programmerService.insertProgrammer(programmer);
        }
        return null;
    }
}
