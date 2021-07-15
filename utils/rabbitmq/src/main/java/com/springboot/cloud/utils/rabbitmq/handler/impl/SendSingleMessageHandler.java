package com.springboot.cloud.utils.rabbitmq.handler.impl;

import com.springboot.cloud.utils.rabbitmq.factory.ChannelFactory;
import com.springboot.cloud.utils.rabbitmq.handler.SendMessageHandler;

import java.io.IOException;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 14:10
 */
public class SendSingleMessageHandler extends BaseSendMessageHandler implements SendMessageHandler {
    public SendSingleMessageHandler(ChannelFactory channelFactory, String channel) {
        super(channelFactory, channel);
    }

    @Override
    public void initializeChannel() {
        try {
            this.channel.queueDeclare(channelLabel, false, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commit() throws IOException {
        if (lock.tryLock()) {
            while (!messageQueue.isEmpty()) {
                channel.basicPublish("", channelLabel, null, messageQueue.poll().getBytes());
            }
            lock.unlock();
        }
    }

    @Override
    public void run() {
        try {
            commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
