package com.spring2x.rabbitmq.exchanges.topic;

import com.spring2x.core.template.GlobalConstant;
import com.spring2x.core.utils.UUIDUtils;
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
 * @date 2022/4/2.
 */
@Component
public class TopicProducer {

    public static final Logger logger = LoggerFactory.getLogger(TopicProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

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
        rabbitTemplate.convertAndSend(TopicRabbitElement.Exchange_Topic, bindingKey, message);
    }


    private MessageProperties initMessageProperties() {
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
