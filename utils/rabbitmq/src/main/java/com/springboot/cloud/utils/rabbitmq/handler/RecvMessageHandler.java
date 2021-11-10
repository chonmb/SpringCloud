package com.springboot.cloud.utils.rabbitmq.handler;

import com.rabbitmq.client.Channel;
import com.springboot.cloud.utils.rabbitmq.observer.BaseObserver;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 16:52
 */
public interface RecvMessageHandler extends Runnable{
    /**
     * 添加监听器
     * @param baseObserver 监听器
     */
    public void addObserver(BaseObserver baseObserver);

    /**
     * 获取channel
     * @return channel对象
     */
    public Channel getChannel();

    /**
     * 监听者资源池关闭
     */
    public void shutdown();
}
