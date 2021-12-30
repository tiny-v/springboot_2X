package com.tinyv.demo.rocketmq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author TinyV
 * @date 2019/11/27
 */
@Configuration
public class RocketMQProducer {

    /** 该应用是否启用生产者 */
    @Value("${rocketmq.producer.isOnOff}")
    private String isOnOff;
    /** 消息的group */
    @Value("${rocketmq.producer.groupName}")
    private String groupName;
    /** name server 地址  */
    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;
    /** 单个消息的最大长度 (KB) */
    @Value("${rocketmq.producer.maxMessageSize}")
    private Integer maxMessageSize;
    /** 消息发送超时时间 (ms) */
    @Value("${rocketmq.producer.sendMsgTimeout}")
    private Integer sendMsgTimeout;
    /** 消息发送失败重试次数 */
    @Value("${rocketmq.producer.retryTimesWhenSendFailed}")
    private Integer retryTimesWhenSendFailed;

    @Bean
    public DefaultMQProducer getRocketMQProducer(){
        // 生产者的组名
        DefaultMQProducer producer = new DefaultMQProducer(this.groupName);
        // 指定NameServer地址，多个地址以 ; 隔开
        producer.setNamesrvAddr(this.namesrvAddr);

        if(maxMessageSize!=null){
            producer.setMaxMessageSize(this.maxMessageSize);
        }
        if(this.sendMsgTimeout!=null){
            producer.setSendMsgTimeout(this.sendMsgTimeout);
        }
        //如果发送消息失败，设置重试次数，默认为2次
        if(this.retryTimesWhenSendFailed!=null){
            producer.setRetryTimesWhenSendFailed(this.retryTimesWhenSendFailed);
        }
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return producer;
    }

}
