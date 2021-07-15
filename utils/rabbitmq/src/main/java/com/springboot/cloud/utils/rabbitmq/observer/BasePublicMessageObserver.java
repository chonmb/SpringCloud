package com.springboot.cloud.utils.rabbitmq.observer;

import com.springboot.cloud.utils.rabbitmq.common.ChannelType;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/14 14:30
 */
public abstract class BasePublicMessageObserver extends BaseObserver {
    @Override
    public ChannelType getChannelType() {
        return ChannelType.PUBLIC;
    }
}
