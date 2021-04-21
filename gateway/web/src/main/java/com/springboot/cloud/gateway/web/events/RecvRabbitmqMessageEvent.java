package com.springboot.cloud.gateway.web.events;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 16:21
 */
@Slf4j
public class RecvRabbitmqMessageEvent implements DeliverCallback {
    private final RouteOperationEvent routeOperationEvent;

    public RecvRabbitmqMessageEvent(RouteOperationEvent routeOperationEvent) {
        this.routeOperationEvent = routeOperationEvent;
    }

    @Override
    public void handle(String s, Delivery delivery) throws IOException {
        String message=new String(delivery.getBody(), StandardCharsets.UTF_8);
        log.info("Received from Gateway Admin: {}", message);
        routeOperationEvent.executeCommand(message);
    }
}
