package com.tinyv.demo.rabbitmq.producer;

import com.tinyv.demo.constant.GlobalConstant;
import com.tinyv.demo.rabbitmq.config.DirectRabbitConfig;
import com.tinyv.demo.rabbitmq.config.FanoutRabbitConfig;
import com.tinyv.demo.rabbitmq.config.HeadersRabbitConfig;
import com.tinyv.demo.rabbitmq.config.TopicRabbitConfig;
import com.tinyv.demo.util.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;

/**
 * @author tiny_v
 * @date 2022/3/2.
 */

@Component
public class RabbitProducer {

    public static final Logger logger = LoggerFactory.getLogger(RabbitProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到Direct Exchange
     * @param messageInfo
     */
    public void sendToDirectExchange(String messageInfo, String bindingKey){
        if(StringUtils.isBlank(messageInfo)){
            return;
        }
        Message message = new Message(messageInfo.getBytes(StandardCharsets.UTF_8), initMessageProperties());
        logger.info("Send Message, BindingKey:[{}], Info:[{}]", bindingKey, message);
        rabbitTemplate.convertAndSend(DirectRabbitConfig.Exchange_Direct, bindingKey, message);
    }

    /**
     * 发送消息到Topic Exchange
     * @param messageInfo
     */
    public void sendToTopicExchange(String messageInfo, String bindingKey){
        if(StringUtils.isBlank(messageInfo)){
            return;
        }
        Message message = new Message(messageInfo.getBytes(StandardCharsets.UTF_8), initMessageProperties());
        logger.info("Send Message, BindingKey:[{}], Info:[{}]", bindingKey, message);
        rabbitTemplate.convertAndSend(TopicRabbitConfig.Exchange_Topic, bindingKey, message);
    }


    /**
     * 发送消息到Fanout Exchange
     * @param messageInfo
     */
    public void sendToFanoutExchange(String messageInfo){
        if(StringUtils.isBlank(messageInfo)){
            return;
        }
        Message message = new Message(messageInfo.getBytes(StandardCharsets.UTF_8), initMessageProperties());
        logger.info("Send Fanout Message, Info:[{}]", message);
        //fanout类型不关心路由， 可以随便写一个
        rabbitTemplate.convertAndSend(FanoutRabbitConfig.Exchange_Fanout,"", message);
    }


    /**
     * 发送消息到Fanout Exchange
     * @param messageInfo
     */
    public void sendToHeaderExchange(String messageInfo){
        if(StringUtils.isBlank(messageInfo)){
            return;
        }
        Message message = new Message(messageInfo.getBytes(StandardCharsets.UTF_8), initMessageHeaderProperties());
        logger.info("Send Headers Message, Info:[{}]", message);
        rabbitTemplate.convertAndSend(HeadersRabbitConfig.Exchange_Headers,null, message);
    }


    private MessageProperties initMessageProperties(){
        MessageProperties properties = new MessageProperties();
        properties.setContentType("application/json");
        properties.setContentEncoding(StandardCharsets.UTF_8.name());
        properties.setAppId(GlobalConstant.AppId);
        properties.setMessageId(UUIDUtils.getUUID32());
        properties.setPriority(10);
        //properties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        return properties;
    }

    /**
     * 设置消息属性
     * @return
     */
    private MessageProperties initMessageHeaderProperties(){
        MessageProperties properties = new MessageProperties();
        properties.setContentType("application/json");
        properties.setAppId("Spring2X");
        properties.setHeader("Queue", "headers");
        properties.setHeader("type", "any");
        return properties;
    }

}

