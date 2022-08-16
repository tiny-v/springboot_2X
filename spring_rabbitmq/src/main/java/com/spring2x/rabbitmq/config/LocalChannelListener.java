package com.spring2x.rabbitmq.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.ChannelListener;
import org.springframework.stereotype.Component;

/**
 * @author tiny_v
 * @date 2022/4/1.
 */
@Component
public class LocalChannelListener implements ChannelListener {

    public static final Logger logger = LoggerFactory.getLogger(LocalChannelListener.class);

    @Override
    public void onCreate(Channel channel, boolean b) {
        logger.info("the channel:{}, transactional:{} is creating", channel, b);
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        ChannelListener.super.onShutDown(signal);
    }

}
