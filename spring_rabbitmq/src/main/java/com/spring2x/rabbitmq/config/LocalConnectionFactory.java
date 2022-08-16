package com.spring2x.rabbitmq.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ChannelListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author tiny_v
 * @date 2022/4/1.
 */
@Component
public class LocalConnectionFactory {

    @Bean
    public RabbitTemplate rabbitTemplate(@Qualifier("cachingConnectionFactory")ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory( connectionFactory);
        return rabbitTemplate;
    }

    /**
     *
     * CHANNEL模式：这也是CachingConnectionFactory的默认模式，在这种模式下，所有的createConnection（）方法实际上返回的都是同一个Connection，同样的Connection.close()方法是没用的，因为就一个，
     * 默认情况下，Connection中只缓存了一个Channel，在并发量不大的时候这种模式是完全够用的，当并发量较高的时候，我们可以setChannelCacheSize（）来增加Connection中缓存的Channel的数量。
     *
     * CONNECTION模式：在CONNECTION模式下，每一次调用createConnection（）方法都会新建一个或者从缓存中获取，根据你设置的ConnectionCacheSize的大小，
     * 当小于的时候会采用新建的策略，当大于等于的时候会采用从缓存中获取的策略，与CHANNEL模式不同的是，CONNECTION模式对Connection和Channel都进行了缓存，
     * 最新版本的client中已经将Channel的缓存数量从1增加到了25，但是在并发量不是特别大的情况下，作用并不是特别明显。
     *
     * @param rabbitProperties
     * @param channelListener
     * @param connectionListener
     * @return
     */
    @Bean
    public CachingConnectionFactory cachingConnectionFactory(@Qualifier("rabbitProperties") RabbitProperties rabbitProperties,
                                                             @Qualifier("localChannelListener") ChannelListener channelListener,
                                                             @Qualifier("localConnectListener") ConnectionListener connectionListener,
                                                             @Qualifier("threadPoolExecutor")ThreadPoolExecutor threadPoolExecutor
                                                             ){
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(rabbitProperties.getHost());
        factory.setPort(rabbitProperties.getPort());
        factory.setUsername(rabbitProperties.getUsername());
        factory.setPassword(rabbitProperties.getPassword());
        factory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
        /**
         * 默认地，最大channelSize是没有限制的，限制的仅仅是缓存的channelSize（connection也一样），默认值是25，
         * 缓存channel的目的是减小高并发多线程环境中频繁创建销毁channel的开销，
         * 比如：在某一时刻有100个channel处于工作状态，当channel空闲后，只会缓存channelSize个channel，剩下的都会被销毁。
         * 通过RabbitMQ的Web管理插件观察到channel在频繁的被创建和销毁时，应及时的提高channelCacheSize。建议最少要保证线程数<channelCacheSize。
         * 可以通过压力测试，观察高峰期channel动态平衡的数量，从而决定channelCacheSize的大小
         * */
        factory.setChannelCacheSize(10);
        /**
         * 通过channelCheckoutTimeout参数设置connectionFactory的channelSize限制，当该参数大于0时，表示最大的channel数目=channelCacheSize.
         * 达到channelCacheSize的上限后，调用createChannel的线程会阻塞，直至有空闲channel出现或阻塞时间超过chanelCheckoutTimeout，
         * 在超时的情况下，抛出AmqpTimeoutException，可以设置一些Retry的策略来处理这些异常。
         */
        // factory.setChannelCheckoutTimeout(2000);
        // factory.setConnectionCacheSize(5);
        factory.setExecutor(threadPoolExecutor);
        factory.setBeanName("localCachingFactory");
        factory.addChannelListener(channelListener);
        factory.addConnectionListener(connectionListener);
        /*
        factory.setPublisherConfirms(rabbitProperties.isPublisherConfirms());
        factory.setPublisherReturns(rabbitProperties.isPublisherReturns());
        factory.setRecoveryListener(rabbitRecoveryListener);
        */
        return factory;
    }



}
