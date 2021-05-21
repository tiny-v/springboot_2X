package com.tinyv.demo.global.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fuwenming
 * @created 2018/4/27
 **/
@Configuration
public class RabbitMQConfig {

  /** 交换机1 */
  public static final String EXCHANGE_RESOURCE_IMAGE = "resource-image-topic";
  /** 交换机2 */
  public static final String EXCHANGE_RESOURCE_TEMP = "temp-exchange";
  /** 路由 */
  public static final String Dispatch_Image_Routing_Key = "resource.image.dispatch";
  /** 队列 */
  public static final String Image_Queue = "image-dispatch-queue";

  /**
   * 声明交换机
   * @return
   */
  @Bean
  public Exchange imageExchange() {
    return ExchangeBuilder.topicExchange(EXCHANGE_RESOURCE_IMAGE).durable(true).build();
  }

  @Bean
  public Exchange tempExchange() {
    return ExchangeBuilder.topicExchange(EXCHANGE_RESOURCE_TEMP).durable(true).build();
  }

  /**
   * 声明队列
   * @return
   */
  @Bean
  public Queue getQueue() {
    return new Queue(Image_Queue, true);
  }


  @Bean
  Binding binding(Queue queue, @Qualifier("imageExchange") Exchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(Dispatch_Image_Routing_Key).noargs();
  }
}
