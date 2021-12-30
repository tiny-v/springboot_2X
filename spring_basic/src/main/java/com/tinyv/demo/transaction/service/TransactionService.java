package com.tinyv.demo.transaction.service;

/**
 * @author tiny_v
 * @date 2021/9/6.
 */
public interface TransactionService {

    /**
     * 场景
     */
    void scenario();

    /**
     * 默认传播策略 required
     */
    void parent();

    /**
     * 捕获异常的场景
     */
    void catchScenario();

}
