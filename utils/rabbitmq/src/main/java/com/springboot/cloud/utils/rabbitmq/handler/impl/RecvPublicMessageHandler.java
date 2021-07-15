package com.springboot.cloud.utils.rabbitmq.handler.impl;

import com.springboot.cloud.utils.rabbitmq.factory.ChannelFactory;
import com.springboot.cloud.utils.rabbitmq.handler.RecvMessageHandler;
import com.springboot.cloud.utils.rabbitmq.observer.BaseObserver;

import java.io.IOException;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 16:45
 */
public class RecvPublicMessageHandler extends BaseRecvMessageHandler implements RecvMessageHandler {
    private String queueName;
    protected RecvPublicMessageHandler(ChannelFactory channelFactory, String channelLabel) {
        super(channelFactory, channelLabel);
    }

    @Override
    protected void initializeChannel() {
        try {
            channel.exchangeDeclare(channelLabel, "fanout");
            queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, channelLabel, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addObserver(BaseObserver baseObserver) {
        this.observerMap.put(baseObserver.getClass().getName(),baseObserver);
    }
}
