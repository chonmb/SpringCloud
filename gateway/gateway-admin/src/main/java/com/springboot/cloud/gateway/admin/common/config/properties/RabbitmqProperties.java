package com.springboot.cloud.gateway.admin.common.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 9:45
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
