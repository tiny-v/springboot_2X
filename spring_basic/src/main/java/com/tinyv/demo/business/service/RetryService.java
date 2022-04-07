package com.tinyv.demo.business.service;

/**
 * @author tiny_v
 * @date 2022/2/24.
 */
public interface RetryService {

    int doBiz();

    void doBiz2();

    int doBiz3(int param) throws Throwable;

}
