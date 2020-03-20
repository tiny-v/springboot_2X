package com.tinyv.demo.global.config.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 *
 * @author TinyV
 * @date 2019/11/27
 */
@Configuration
public class RocketMQConsumer {

    /** 该应用是否启用消费者*/
    @Value("${rocketmq.consumer.isOnOff}")
    private String isOnOff;
    @Value("${rocketmq.consumer.groupName}")
    private String groupName;
    /** name server地址*/
    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;
    /** 该消费者订阅的主题和tags ("*"号表示订阅该主题下所有的tags),格式：topic~tag1||tag2||tag3;topic2~*/
    @Value("${rocketmq.consumer.topics}")
    private String topics;
    /***/
    @Value("${rocketmq.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.consumer.consumeThreadMax}")
    private int consumeThreadMax;
    /** 设置一次消费消息的条数，默认为1条*/
    @Value("${rocketmq.consumer.consumeMessageBatchMaxSize}")
    private int consumeMessageBatchMaxSize;

    @Resource
    private RocketMsgListener msgListener;

    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener(msgListener);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        try {
            String[] topicTagsArr = topics.split(";");
            for (String topicTags : topicTagsArr) {
                String[] topicTag = topicTags.split("~");
                consumer.subscribe(topicTag[0],topicTag[1]);
            }
            consumer.start();
        }catch (MQClientException e){
            e.printStackTrace();
        }
        return consumer;
    }

}
