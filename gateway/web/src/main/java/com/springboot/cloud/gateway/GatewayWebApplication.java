package com.springboot.cloud.gateway;

import com.springboot.cloud.utils.rabbitmq.annotation.EnableSpringCloudRabbitmq;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 8:49
 */
@SpringBootApplication
@EnableSpringCloudRabbitmq
@EnableFeignClients
@EnableDiscoveryClient
public class GatewayWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayWebApplication.class,args);
    }
}
