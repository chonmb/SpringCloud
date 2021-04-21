package com.springboot.cloud.gateway.admin.common.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.springboot.cloud.common.core.constant.CommonConstant;
import com.springboot.cloud.gateway.admin.common.config.properties.RabbitmqProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 9:44
 */
@Configuration
public class RabbitmqConfiguration {
    private final RabbitmqProperties rabbitmqProperties;

    public RabbitmqConfiguration(RabbitmqProperties rabbitmqProperties) {
        this.rabbitmqProperties = rabbitmqProperties;
    }

    @Bean
    public Channel channelConfig() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitmqProperties.getHost());
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(CommonConstant.GATEWAY_RABBITMQ_NAME, false, false, false, null);
        return channel;
    }
}
