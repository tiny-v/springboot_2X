package com.spring2x.core.template;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author tiny_v
 * @date 2021/8/6.
 */
@Component
public class RestTemplateConfig {

    /** 最大连接数 */
    private Integer maxConnect = 500;

    /** 最大并发数 */
    private Integer maxRequest = 100;

    /** connect time out */
    private Integer connectTimeOut =  5 * 1000;

    /** read time out */
    private Integer readTimeOut =  30 * 1000;

    @Autowired
    RestResponseErrorHandler errorResponseHandler;

    @Bean
    public RestTemplate restTemplate(){
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(maxConnect);   // 设置最大链接数
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxRequest); // 单路由的并发数
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
        HttpClient httpClient = httpClientBuilder.build();
        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());  // 保持长链接配置，keep-alive
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        httpComponentsClientHttpRequestFactory.setReadTimeout(readTimeOut);
        httpComponentsClientHttpRequestFactory.setConnectTimeout(connectTimeOut);
        ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(httpComponentsClientHttpRequestFactory);
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setErrorHandler(errorResponseHandler);
        return restTemplate;
    }

}
