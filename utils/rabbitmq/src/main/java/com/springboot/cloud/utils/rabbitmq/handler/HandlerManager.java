package com.springboot.cloud.utils.rabbitmq.handler;

import com.springboot.cloud.utils.rabbitmq.common.ChannelType;
import com.springboot.cloud.utils.rabbitmq.observer.BaseObserver;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 10:09
 */
public interface HandlerManager {
    /**
     * 向指定的channel发送消息
     *
     * @param channel channel标签
     * @param message 消息体
     */
    public void send(String channel, String message);

    /**
     * 构建发送channel
     *
     * @param channel channel标签
     * @param type    channel类型
     */
    public void buildSendChannel(String channel, ChannelType type);

    /**
     * 检测channel是否存在
     *
     * @param channel channel标签
     * @return 判断结果
     */
    public boolean isSendChannelExist(String channel);

    /**
     * 构建接收channel
     *
     * @param channel channel标签
     * @param type    channel类型
     */
    public void buildRecvChannel(String channel, ChannelType type);

    /**
     * 检测channel是否存在
     *
     * @param channel channel标签
     * @return 判断结果
     */
    public boolean isRecvChannelExist(String channel);

    /**
     * 注册监听器
     * @param observer 监听器
     */
    public void registerObserver(BaseObserver observer);
}
