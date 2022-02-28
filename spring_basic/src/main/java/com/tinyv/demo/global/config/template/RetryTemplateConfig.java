package com.tinyv.demo.global.config.template;

import org.springframework.context.annotation.Bean;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

/**
 * @author tiny_v
 * @date 2022/2/25.
 */
@Component
public class RetryTemplateConfig {

    private SimpleRetryPolicy simpleRetryPolicy;

    private ExponentialBackOffPolicy backOffPolicy;

    @Bean
    public RetryTemplate configRetryTemplate(){
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(getSimpleRetryPolicy());
        retryTemplate.setBackOffPolicy(getBackOffPolicy());
        return retryTemplate;
    }

    /**
     * 简单重试策略-默认重试3次
     * @return
     */
    private RetryPolicy getSimpleRetryPolicy(){
        if(simpleRetryPolicy == null){
            simpleRetryPolicy = new SimpleRetryPolicy();
            simpleRetryPolicy.setMaxAttempts(4);//修改成最大重试4次
        }
        return new SimpleRetryPolicy();
    }

    private BackOffPolicy getBackOffPolicy(){
        if(backOffPolicy == null){
            backOffPolicy = new ExponentialBackOffPolicy();
            //重试间隔
            backOffPolicy.setInitialInterval(2000);
            //重试间隔时间乘数
            backOffPolicy.setMultiplier(2);
            //最大间隔
            backOffPolicy.setMaxInterval(15000);
        }
        return backOffPolicy;
    }

}
