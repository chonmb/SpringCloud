package com.springboot.cloud.utils.rabbitmq.observer;

import com.springboot.cloud.utils.rabbitmq.common.ChannelType;
import org.springframework.util.StringUtils;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 10:11
 */
public abstract class BaseObserver implements Runnable {
    private String latestMessage;

    @Override
    public void run() {
        if (StringUtils.hasText(latestMessage)) {
            execute(latestMessage);
        }
    }

    public void notify(String latestMessage) {
        this.latestMessage = latestMessage;
    }

    /**
     * 执行消息hook
     *
     * @param message rabbit消息
     */
    public abstract void execute(String message);

    /**
     * 获取observer监听channel
     *
     * @return channel标签
     */
    public abstract String getChannelName();

    /**
     * 用于初始化channel时对channel进行初始化配置
     *
     * @return channel类型
     */
    public abstract ChannelType getChannelType();
}
