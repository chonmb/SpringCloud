package com.springboot.cloud.gateway;

import com.springboot.cloud.utils.rabbitmq.annotation.EnableSpringCloudRabbitmq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/15 11:40
 */

@SpringBootApplication
@EnableSpringCloudRabbitmq
public class GatewayAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayAdminApplication.class, args);
    }
}
