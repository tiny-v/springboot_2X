package com.tinyv.demo.business.service;

import com.alibaba.rocketmq.client.producer.SendResult;

/**
 *
 * @author YMa69
 * @date 2019/11/27
 */
public interface MsgQueueService {

    /**
     * 发送消息
     * @param msgInfo
     */
    SendResult sendMsg(String msgInfo);

}
