package com.springboot.cloud.utils.rabbitmq.handler.impl;

import com.rabbitmq.client.Channel;
import com.springboot.cloud.utils.rabbitmq.factory.ChannelFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 13:01
 */
public abstract class BaseSendMessageHandler {
    protected final Queue<String> messageQueue = new LinkedList<>();
    protected final Lock lock = new ReentrantLock();
    protected final ChannelFactory channelFactory;
    protected final String channelLabel;
    protected Channel channel;

    protected BaseSendMessageHandler(ChannelFactory channelFactory, String channel){
        this.channelFactory = channelFactory;
        this.channelLabel = channel;
        try {
            this.channel = channelFactory.createChannel(channel);
            initializeChannel();
            commit();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

    }

    /**
     * channel初始化
     */
    public abstract void initializeChannel();

    /**
     * 消息提交
     * @throws IOException
     */
    public abstract void commit() throws IOException;

    public void send(String message) {
        lock.lock();
        try{
            messageQueue.offer(message);
        }finally {
            lock.unlock();
        }
    }

    public void sendAndFlush(String message) {
        send(message);
        flush();
    }

    public void flush() {
        try {
            commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ChannelFactory getChannelFactory() {
        return channelFactory;
    }
}
