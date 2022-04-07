package com.tinyv.demo.rabbitmq.dlx;

import com.tinyv.demo.constant.GlobalConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
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
public class DlxRabbitProducer {

    public static final Logger logger = LoggerFactory.getLogger(DlxRabbitProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到 TTL Exchange
     * @param messageInfo
     */
    public void sendToTtlExchange(String exchange, String messageInfo, String bindingKey){
        if(StringUtils.isBlank(messageInfo)){
            return;
        }
        Message message = new Message(messageInfo.getBytes(StandardCharsets.UTF_8), initMessageProperties());
        logger.info("Send Message, BindingKey:[{}], Info:[{}]", bindingKey, message);
        rabbitTemplate.convertAndSend(exchange, bindingKey, message);
    }

    private MessageProperties initMessageProperties(){
        MessageProperties properties = new MessageProperties();
        properties.setContentType("application/json");
        properties.setAppId(GlobalConstant.AppId);
        return properties;
    }


}
