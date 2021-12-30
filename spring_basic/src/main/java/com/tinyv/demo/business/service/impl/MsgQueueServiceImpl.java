package com.tinyv.demo.business.service.impl;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.tinyv.demo.business.service.MsgQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 *
 * @author TinyV
 * @date 2019/11/27
 */
@Service
public class MsgQueueServiceImpl implements MsgQueueService {

    private static final Logger logger = LoggerFactory.getLogger(MsgQueueServiceImpl.class);

    @Resource
    private DefaultMQProducer defaultMQProducer;

    /**
     * 发送消息
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
            logger.error("send message occurs error.", e);
            Thread.currentThread().interrupt();
        }
        return sendResult;

    }
}
