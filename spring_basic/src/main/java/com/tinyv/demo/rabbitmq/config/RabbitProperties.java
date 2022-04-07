package com.tinyv.demo.rabbitmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author tiny_v
 * @date 2022/4/1.
 */
@Component
@ConfigurationProperties(prefix="spring.rabbitmq")
public class RabbitProperties {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private Integer cacheChannelSize;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCacheChannelSize() {
        return cacheChannelSize;
    }

    public void setCacheChannelSize(Integer cacheChannelSize) {
        this.cacheChannelSize = cacheChannelSize;
    }
}
