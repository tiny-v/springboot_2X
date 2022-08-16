package com.spring2x.core.template;

import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.NeverRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

/**
 * @author tiny_v
 * @date 2022/2/25.
 */
@Component
public class RetryTemplateConfig {


    @Bean
    public RetryTemplate configRetryTemplate(){
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(getRetryPolicy());
        retryTemplate.setBackOffPolicy(getBackOffPolicy());
        return retryTemplate;
    }

    private ExceptionClassifierRetryPolicy getRetryPolicy(){
        ExceptionClassifierRetryPolicy policy = new ExceptionClassifierRetryPolicy();
        policy.setExceptionClassifier((Classifier<Throwable, RetryPolicy>) classifiable -> {
            if (classifiable instanceof IOException || classifiable instanceof RestClientException) {
                return getSimpleRetryPolicy();
            }
            return new NeverRetryPolicy();
        });
        return policy;
    }

    /**
     * 简单重试策略， 默认重试3次
     * @return
     */
    private SimpleRetryPolicy getSimpleRetryPolicy(){
        return new SimpleRetryPolicy();
    }


    /**
     * 基础间隔时间为4s, 时间间隔系数为1.5， 最大时间间隔为10s
     * 即：第一次间隔4s, 第二次间隔 4*1.5=6s; 第三次间隔 6*1.5=9s; 但最大不会超过10s
     * @return
     */
    private BackOffPolicy getBackOffPolicy(){
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        //基础间隔时间
        backOffPolicy.setInitialInterval(4*1000);
        //间隔时间乘数
        backOffPolicy.setMultiplier(1.5);
        //最大间隔
        backOffPolicy.setMaxInterval(10*1000);
        return backOffPolicy;
    }

}
