package com.springboot.cloud.utils.rabbitmq.aspect;

import com.springboot.cloud.utils.rabbitmq.annotation.SendPublishRabbitmqMessage;
import com.springboot.cloud.utils.rabbitmq.annotation.SendSingleRabbitmqMessage;
import com.springboot.cloud.utils.rabbitmq.common.ChannelType;
import com.springboot.cloud.utils.rabbitmq.handler.HandlerManager;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/13 15:33
 */
@Aspect
@Component
public class SendMessageAspect {
    private final HandlerManager handlerManager;

    public SendMessageAspect(HandlerManager handlerManager) {
        this.handlerManager = handlerManager;
    }

    @Pointcut("@annotation(com.springboot.cloud.utils.rabbitmq.annotation.SendSingleRabbitmqMessage)")
    public void singlePointcut() {
        //single pointcut
    }

    @AfterReturning(value = "singlePointcut()&&@annotation(sendSingleRabbitmqMessage)", returning = "result")
    public void singleAfter(SendSingleRabbitmqMessage sendSingleRabbitmqMessage, Object result) {
        if (result instanceof String && StringUtils.hasText(sendSingleRabbitmqMessage.channel())) {
            if (!handlerManager.isSendChannelExist(sendSingleRabbitmqMessage.channel())){
                handlerManager.buildSendChannel(sendSingleRabbitmqMessage.channel(), ChannelType.SINGLE);
            }
            handlerManager.send(sendSingleRabbitmqMessage.channel(), (String) result);
        }
    }

    @Pointcut("@annotation(com.springboot.cloud.utils.rabbitmq.annotation.SendPublishRabbitmqMessage)")
    public void publicPointcut() {
        //public pointcut
    }

    @AfterReturning(value = "publicPointcut()&&@annotation(sendPublishRabbitmqMessage)", returning = "result")
    public void publicAfter(SendPublishRabbitmqMessage sendPublishRabbitmqMessage, Object result) {
        if (result instanceof String && StringUtils.hasText(sendPublishRabbitmqMessage.channel())) {
            if (!handlerManager.isSendChannelExist(sendPublishRabbitmqMessage.channel())){
                handlerManager.buildSendChannel(sendPublishRabbitmqMessage.channel(), ChannelType.PUBLIC);
            }
            handlerManager.send(sendPublishRabbitmqMessage.channel(), (String) result);
        }
    }


}
