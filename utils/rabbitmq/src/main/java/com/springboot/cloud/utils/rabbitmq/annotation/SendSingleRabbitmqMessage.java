package com.springboot.cloud.utils.rabbitmq.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 10:01
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SendSingleRabbitmqMessage {
    @AliasFor("channel")
    String value() default "";

    @AliasFor("value")
    String channel() default "";
}
