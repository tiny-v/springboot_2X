package com.tinyv.demo.rabbitmq.producer;

import com.tinyv.demo.constant.GlobalConstant;
import com.tinyv.demo.rabbitmq.config.BatchRabbitConfig;
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
 * @date 2022/3/15.
 */
@Component
public class BatchRabbitProducer {

    public static final Logger logger = LoggerFactory.getLogger(BatchRabbitProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到 Batch Exchange
     * @param messageInfo
     */
    public void sendToBatchExchange(String messageInfo, String bindingKey){
        if(StringUtils.isBlank(messageInfo)){
            return;
        }
        Message message = new Message(messageInfo.getBytes(StandardCharsets.UTF_8), initMessageProperties());
        logger.info("Send Message, BindingKey:[{}], Info:[{}]", bindingKey, message);
        rabbitTemplate.convertAndSend(BatchRabbitConfig.Batch_Exchange, bindingKey, message);
    }

    private MessageProperties initMessageProperties(){
        MessageProperties properties = new MessageProperties();
        properties.setContentType("application/json");
        properties.setContentEncoding(StandardCharsets.UTF_8.name());
        properties.setAppId(GlobalConstant.AppId);
        properties.setMessageId(UUIDUtils.getUUID32());
        properties.setPriority(10);
        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        return properties;
    }

}
