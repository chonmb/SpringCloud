package com.springboot.cloud.utils.rabbitmq.handler;

import com.rabbitmq.client.Channel;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 10:08
 */
public interface SendMessageHandler extends Runnable {
    /**
     * 发送消息
     * @param message 消息体
     */
    public void send(String message);

    /**
     * 刷新消息
     */
    public void flush();

    /**
     * 发送并立即刷新
     * @param message
     */
    public void sendAndFlush(String message);

    /**
     * 获取channel
     * @return channel对象
     */
    public Channel getChannel();
}
