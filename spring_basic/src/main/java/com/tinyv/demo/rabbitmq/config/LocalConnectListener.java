package com.tinyv.demo.rabbitmq.config;

import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.stereotype.Component;

/**
 * @author tiny_v
 * @date 2022/4/1.
 */
@Component
public class LocalConnectListener implements ConnectionListener {

    public static final Logger logger = LoggerFactory.getLogger(LocalConnectListener.class);

    @Override
    public void onCreate(Connection connection) {
        logger.info("the connection:{} is creating.", connection);
    }

    @Override
    public void onClose(Connection connection) {
        logger.info("the connection:{} is closing.", connection);
        ConnectionListener.super.onClose(connection);
    }

    @Override
    public void onShutDown(ShutdownSignalException signal) {
        logger.info("connection on shutdown signal:{}.", signal);
        ConnectionListener.super.onShutDown(signal);
    }
}
