package com.springboot.cloud.gateway.web.events;

import com.springboot.cloud.common.core.constant.CommonConstant;
import com.springboot.cloud.gateway.web.common.factory.RouteCommandFactory;
import com.springboot.cloud.gateway.web.models.pojo.RouteCommand;
import com.springboot.cloud.gateway.web.service.IRouteService;
import com.springboot.cloud.utils.rabbitmq.observer.BasePublicMessageObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 12:53
 */
@Component
@Slf4j
public class RouteOperationEvent extends BasePublicMessageObserver implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    private final IRouteService routeService;
    private final RouteCommandFactory routeCommandFactory;

    public RouteOperationEvent(IRouteService routeService, RouteCommandFactory routeCommandFactory) {
        this.routeService = routeService;
        this.routeCommandFactory = routeCommandFactory;
    }

    public void executeCommand(String message) {
        RouteCommand command = routeCommandFactory.buildCommand(message);
        switch (command.getOperation()) {
            case INSERT_ROUTE:
                routeService.save(command.getRouteDefinition());
                break;
            case DELETE_ROUTE:
                routeService.delete(command.getRouteId());
                break;
            case UPDATE_ROUTE:
                routeService.delete(command.getRouteId());
                routeService.save(command.getRouteDefinition());
                break;
            case OVERLOAD:
                routeService.clearAndSave(command.getRouteDefinition());
                break;
            default:
                break;
        }
        commit();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    private void commit() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void execute(String message) {
        executeCommand(message);
    }

    @Override
    public String getChannelName() {
        return CommonConstant.GATEWAY_RABBITMQ_NAME;
    }
}
