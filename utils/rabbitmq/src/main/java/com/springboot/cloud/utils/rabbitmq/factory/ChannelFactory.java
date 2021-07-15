package com.springboot.cloud.utils.rabbitmq.factory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.springboot.cloud.utils.rabbitmq.config.RabbitmqProperties;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 10:47
 */
@Component
public class ChannelFactory {
    private final ConnectionFactory connectionFactory;
    private final RabbitmqProperties rabbitmqProperties;
    private Connection connection;
    private Map<String, Channel> channelMap;

    public ChannelFactory(RabbitmqProperties rabbitmqProperties) {
        this.rabbitmqProperties = rabbitmqProperties;
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitmqProperties.getHost());
        connectionFactory.setPort(rabbitmqProperties.getPort());
        connectionFactory.setUsername(rabbitmqProperties.getUsername());
        connectionFactory.setPassword(rabbitmqProperties.getPassword());
        channelMap = new ConcurrentHashMap<>(8);
    }

    public Connection getConnection() throws IOException, TimeoutException {
        if (connection == null) {
            this.connection = connectionFactory.newConnection();
        }
        return connection;
    }

    public boolean hasChannel(String label) {
        return channelMap.containsKey(label);
    }

    public Optional<Channel> getChannel(String label) {
        return Optional.ofNullable(channelMap.get(label));
    }

    public Channel createChannel(String label) throws IOException, TimeoutException {
        if (hasChannel(label)) {
            return channelMap.get(label);
        } else {
            Channel channel = getConnection().createChannel();
            channelMap.put(label, channel);
            return channel;
        }
    }

    @SneakyThrows
    private void destroy() {
        for (Channel channel : channelMap.values()) {
            channel.close();
        }
        connection.close();
    }
}
