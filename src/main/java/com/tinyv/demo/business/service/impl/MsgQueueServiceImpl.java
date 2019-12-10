package com.tinyv.demo.business.service.impl;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.tinyv.demo.business.service.MsgQueueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author YMa69
 * @date 2019/11/27
 */
@Service
public class MsgQueueServiceImpl implements MsgQueueService {

    @Resource
    private DefaultMQProducer defaultMQProducer;
    /**
     * 发送消息
     *
     * @param msgInfo
     */
    @Override
    public SendResult sendMsg(String msgInfo) {

        defaultMQProducer.setProducerGroup("Spring2X");
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message("topicDemo", "tag1;tag2", "fee_open_account_key", msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;

    }
}
