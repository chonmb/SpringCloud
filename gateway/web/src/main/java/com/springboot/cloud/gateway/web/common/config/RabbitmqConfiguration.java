package com.springboot.cloud.gateway.web.common.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.springboot.cloud.common.core.constant.CommonConstant;
import com.springboot.cloud.gateway.web.common.config.properties.RabbitmqProperties;
import com.springboot.cloud.gateway.web.events.RecvRabbitmqMessageEvent;
import com.springboot.cloud.gateway.web.events.RouteOperationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 15:15
 */
@Configuration
public class RabbitmqConfiguration {
    private final RabbitmqProperties rabbitmqProperties;
    private final RouteOperationEvent routeOperationEvent;

    public RabbitmqConfiguration(RabbitmqProperties rabbitmqProperties, RouteOperationEvent routeOperationEvent) {
        this.rabbitmqProperties = rabbitmqProperties;
        this.routeOperationEvent = routeOperationEvent;
    }

    @Bean
    public Channel channelConfig() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitmqProperties.getHost());
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(CommonConstant.GATEWAY_RABBITMQ_NAME, false, false, false, null);
            channel.basicConsume(CommonConstant.GATEWAY_RABBITMQ_NAME, true, new RecvRabbitmqMessageEvent(routeOperationEvent), consumerTag -> {
            });
            return channel;
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
