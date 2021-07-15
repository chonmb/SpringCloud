package com.springboot.cloud.utils.rabbitmq.handler;

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
}
