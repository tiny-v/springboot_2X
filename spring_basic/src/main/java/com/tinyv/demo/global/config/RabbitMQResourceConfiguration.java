package com.tinyv.demo.global.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fuwenming
 * @created 2018/4/27
 **/
@Configuration
public class RabbitMQResourceConfiguration {


  public static final String EXCHANGE_RESOURCE = "resource-topic";

  public static final String EXCHANGE_RESOURCE_IMAGE = "resource-image-topic";

  public static final String ASYNC_TASK_RESOURCE = "resource-async-task-topic";

  public static final String QUOTA_EXCHANGE_RESOURCE = "resource-scheduler-topic";

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(EXCHANGE_RESOURCE, true, false);
  }
}
