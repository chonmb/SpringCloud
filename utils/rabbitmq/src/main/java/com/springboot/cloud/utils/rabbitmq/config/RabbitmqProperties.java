package com.springboot.cloud.utils.rabbitmq.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 9:52
 */
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Getter
@Setter
public class RabbitmqProperties {
    private String host;
    private int port;
    private String username;
    private String password;
}
