package com.springboot.cloud.utils.rabbitmq.handler.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.springboot.cloud.utils.rabbitmq.factory.ChannelFactory;
import com.springboot.cloud.utils.rabbitmq.observer.BaseObserver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 16:06
 */
public abstract class BaseRecvMessageHandler {
    protected final Map<String, BaseObserver> observerMap;
    protected final ChannelFactory channelFactory;
    protected final String channelLabel;
    protected final ThreadPoolExecutor works;
    protected Channel channel;
    protected DeliverCallback deliverCallback;

    protected BaseRecvMessageHandler(ChannelFactory channelFactory, String channelLabel) {
        this.channelFactory = channelFactory;
        this.channelLabel = channelLabel;
        this.observerMap = new ConcurrentHashMap<>(8);
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("recv-handler-%d").build();
        this.works = new ThreadPoolExecutor(1, 8,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                namedThreadFactory);
        deliverCallback=(s, delivery) -> {
            String message=new String(delivery.getBody(), StandardCharsets.UTF_8);
            notifyAll(message);
        };
        try{
            this.channel=channelFactory.createChannel(channelLabel);
            initializeChannel();
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * channel初始化
     */
    protected abstract void initializeChannel();

    private void notifyAll(String message){
        observerMap.values().forEach(baseObserver -> {
            baseObserver.notify(message);
            works.execute(baseObserver);
        });
    }

}
