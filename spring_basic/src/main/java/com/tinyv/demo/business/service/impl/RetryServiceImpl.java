package com.tinyv.demo.business.service.impl;

import com.tinyv.demo.business.service.RetryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

/**
 * @author tiny_v
 * @date 2022/2/24.
 */
@Service
public class RetryServiceImpl implements RetryService {

    private static final Logger logger = LoggerFactory.getLogger(RetryServiceImpl.class);

    @Autowired
    private RetryTemplate retryTemplate;

    /**
     * @EnableRetry：此注解用于开启重试框架，可以修饰在SpringBoot启动类上面，也可以修饰在需要重试的类上
     *    proxyTargetClass：Boolean类型，用于指明代理方式【true：cglib代理，false：jdk动态代理】默认使用jdk动态代理
     * @Retryable
     *    value：Class[]类型，用于指定需要重试的异常类型，
     *    include：Class[]类型，作用于value类似，区别尚未分析
     *    exclude：Class[]类型，指定不需要重试的异常类型
     *    maxAttemps：int类型，指定最多重试次数，默认3
     *    backoff：Backoff类型，指明补偿机制
     *    @BackOff
     *       delay:指定延迟后重试，默认为1000L,即1s后开始重试 ;
     *       multiplier:指定延迟的倍数
     * @Recover
     *    当重试次数耗尽依然出现异常时，执行此异常对应的@Recover方法。
     *    异常类型需要与Recover方法参数类型保持一致，
     *    recover方法返回值需要与重试方法返回值保证一致
     */
    @Override
    @Retryable(
            value = Exception.class,
            maxAttempts = 2,
            backoff = @Backoff(delay = 2000L, multiplier = 2)
    )
    public int doBiz() {
        logger.info("say something:{{}}", "I'm ArithmeticException!!!");
        System.out.println(1/0);
        return 1;
    }

    @Override
    public void doBiz2() {
        //由于使用了aspect增强技术，所以会有对应的坑 -- 内部调用时，重试不会生效
        doBiz2Retry();
    }

    /**
     * 使用RetryTemplate来进行重试
     * @param param
     */
    @Override
    public int doBiz3(int param){
        return retryTemplate.execute(retryContext -> {
            Integer result;
            try{
                result = param / 0;
            }catch (ArithmeticException e){
                logger.info("I'm ArithmeticException!!! current time:{}", System.currentTimeMillis()/1000);
                throw e;
            } catch (Exception e){
                logger.info("exception:[{}]", e);
                throw e;
            }
            return result;
        });
    }

    @Retryable(
            value = Exception.class,
            maxAttempts = 2,
            backoff = @Backoff(delay = 2000L, multiplier = 2)
    )
    private int doBiz2Retry(){
        logger.info("say something:{{}}", "I'm NullPointerException!!!");
        Integer a = null;
        System.out.println(a/2);
        return 2;
    }


    /**
     * 1. 异常类型需要与Recover方法参数类型保持一致
     * 2. recover方法返回值需要与重试方法返回值保证一致
     * 3. 要和@Retryable写在同一个类中，才会生效
     */
    @Recover
    public int recover(Exception e) {
        if(e instanceof ArithmeticException){
            logger.info("The exception is ArithmeticException");
            return 1;
        }else{
            logger.info("Actually, the exception is :[{}]", e);
            return 2;
        }

    }



}
