package com.springboot.cloud.utils.rabbitmq.annotation;

import com.springboot.cloud.utils.rabbitmq.config.AutoConfigurationRabbitmq;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 9:48
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AutoConfigurationRabbitmq.class})
public @interface EnableSpringCloudRabbitmq {
}
