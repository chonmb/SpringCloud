package com.springboot.cloud.utils.rabbitmq.context;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/15 15:19
 */
public interface RabbitmqContextAware {
    /**
     * 观察者执行后
     */
    public void afterObserversRegistered();
}
