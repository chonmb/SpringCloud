package com.springboot.cloud.utils.rabbitmq.handler.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.rabbitmq.client.Channel;
import com.springboot.cloud.utils.rabbitmq.common.ChannelType;
import com.springboot.cloud.utils.rabbitmq.factory.ChannelFactory;
import com.springboot.cloud.utils.rabbitmq.handler.HandlerManager;
import com.springboot.cloud.utils.rabbitmq.handler.RecvMessageHandler;
import com.springboot.cloud.utils.rabbitmq.handler.SendMessageHandler;
import com.springboot.cloud.utils.rabbitmq.observer.BaseObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 12:42
 */
@Component
@Slf4j
public class HandlerManagerImpl implements HandlerManager, ApplicationListener<ContextClosedEvent> {
    private final ChannelFactory channelFactory;
    private final Map<String, SendMessageHandler> sendMessageHandlerMap;
    private ThreadPoolExecutor sendPool;
    private final Map<String, RecvMessageHandler> recvMessageHandlerMap;
    private ThreadPoolExecutor recvBoss;

    public HandlerManagerImpl(ChannelFactory channelFactory) {
        this.channelFactory = channelFactory;
        this.sendMessageHandlerMap = new ConcurrentHashMap<>(8);
        this.recvMessageHandlerMap = new ConcurrentHashMap<>(8);
        initializationPool();
    }

    private void initializationPool() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("rabbitmq-handler-%d").build();
        sendPool = new ThreadPoolExecutor(1, 8, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), threadFactory);
        recvBoss = new ThreadPoolExecutor(1, 8, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), threadFactory);
    }

    @Override
    public void send(String channel, String message) {
        if (sendMessageHandlerMap.containsKey(channel)) {
            sendMessageHandlerMap.get(channel).send(message);
            sendPool.execute(sendMessageHandlerMap.get(channel));
        } else {
            log.error("channel - {} not found", channel);
        }
    }

    @Override
    public void buildSendChannel(String channel, ChannelType type) {
        if (!isSendChannelExist(channel)) {
            switch (type) {
                case PUBLIC:
                    sendMessageHandlerMap.put(channel, new SendPublicMessageHandler(channelFactory, channel));
                    break;
                case SINGLE:
                    sendMessageHandlerMap.put(channel, new SendSingleMessageHandler(channelFactory, channel));
                    break;
                default:
                    break;
            }
        } else {
            log.debug("channel - {} already build", channel);
        }
    }

    @Override
    public boolean isSendChannelExist(String channel) {
        return sendMessageHandlerMap.containsKey(channel);
    }

    @Override
    public void buildRecvChannel(String channel, ChannelType type) {
        if (!isRecvChannelExist(channel)) {
            switch (type) {
                case SINGLE:
                    recvMessageHandlerMap.put(channel, new RecvSingleMessageHandler(channelFactory, channel));
                    recvBoss.execute(recvMessageHandlerMap.get(channel));
                    break;
                case PUBLIC:
                    recvMessageHandlerMap.put(channel, new RecvPublicMessageHandler(channelFactory, channel));
                    recvBoss.execute(recvMessageHandlerMap.get(channel));
                    break;
                default:
                    break;
            }
        } else {
            log.debug("channel - {} already build", channel);
        }
    }

    @Override
    public Channel getSendChannel(String channel) {
        Optional<SendMessageHandler> msgHandler = Optional.ofNullable(sendMessageHandlerMap.get(channel));
        return msgHandler.map(SendMessageHandler::getChannel).orElse(null);
    }

    @Override
    public Channel getRecvChannel(String channel) {
        Optional<RecvMessageHandler> msgHandler = Optional.ofNullable(recvMessageHandlerMap.get(channel));
        return msgHandler.map(RecvMessageHandler::getChannel).orElse(null);
    }

    @Override
    public Optional<Channel> getChannel(String channel) {
        return channelFactory.getChannel(channel);
    }

    @Override
    public boolean isRecvChannelExist(String channel) {
        return recvMessageHandlerMap.containsKey(channel);
    }

    @Override
    public void registerObserver(BaseObserver observer) {
        if (!isRecvChannelExist(observer.getChannelName())) {
            buildRecvChannel(observer.getChannelName(), observer.getChannelType());
        }
        recvMessageHandlerMap.get(observer.getChannelName()).addObserver(observer);
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        shutdownGenerally();
    }

    public void shutdownGenerally() {
        sendPool.shutdown();
        recvBoss.shutdown();
        recvMessageHandlerMap.values().forEach(RecvMessageHandler::shutdown);
        channelFactory.destroy();
    }
}
