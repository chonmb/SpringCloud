package com.springboot.cloud.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/14 12:56
 */
@SpringBootApplication
@EnableAdminServer
public class ServiceAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAdminApplication.class,args);
    }
}
