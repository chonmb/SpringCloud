package com.springboot.cloud.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chonmb Email:jiangxin2@shanghai-electric.com
 * @date 2021/4/14 12:56
 */
@SpringBootApplication
@EnableAdminServer
public class SpringbootAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootAdminApplication.class,args);
    }
}
