package com.springboot.cloud.utils.rabbitmq.config;

import com.springboot.cloud.utils.rabbitmq.context.RabbitmqContextAware;
import com.springboot.cloud.utils.rabbitmq.handler.HandlerManager;
import com.springboot.cloud.utils.rabbitmq.observer.BaseObserver;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/14 14:48
 */
@Component
public class RabbitmqObserverConfiguration implements ApplicationContextAware {
    private final HandlerManager handlerManager;

    public RabbitmqObserverConfiguration(HandlerManager handlerManager) {
        this.handlerManager = handlerManager;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, BaseObserver> observers = applicationContext.getBeansOfType(BaseObserver.class);
        observers.forEach((key, value) -> handlerManager.registerObserver(value));
        applicationContext.getBeansOfType(RabbitmqContextAware.class).forEach((s, rabbitmqContextAware) -> rabbitmqContextAware.afterObserversRegistered());
    }
}
