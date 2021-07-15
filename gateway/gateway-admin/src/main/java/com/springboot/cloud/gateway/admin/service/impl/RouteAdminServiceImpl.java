package com.springboot.cloud.gateway.admin.service.impl;

import com.springboot.cloud.common.core.constant.RouteOperationConstant;
import com.springboot.cloud.common.core.models.RouteInfo;
import com.springboot.cloud.gateway.admin.common.mapper.RouteConverter;
import com.springboot.cloud.gateway.admin.common.repo.GatewayRouteRepo;
import com.springboot.cloud.gateway.admin.events.GatewayAdminEvent;
import com.springboot.cloud.gateway.admin.service.IRouteAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/21 11:08
 */
@Service
@Slf4j
public class RouteAdminServiceImpl implements IRouteAdminService {
    private final GatewayAdminEvent gatewayAdminEvent;
    private final GatewayRouteRepo gatewayRouteRepo;
    private final RouteConverter routeConverter;

    public RouteAdminServiceImpl(GatewayAdminEvent gatewayAdminEvent, GatewayRouteRepo gatewayRouteRepo, RouteConverter routeConverter) {
        this.gatewayAdminEvent = gatewayAdminEvent;
        this.gatewayRouteRepo = gatewayRouteRepo;
        this.routeConverter = routeConverter;
    }

    @Override
    @PostConstruct
    public void overloadRoutes() {
        List<RouteInfo> gatewayRoutes = routeConverter.toRouteInfo(gatewayRouteRepo.findAll());
        String command = gatewayAdminEvent.sendRouteCommand(
                RouteOperationConstant.OVERLOAD,
                gatewayRoutes.stream().map(RouteInfo::getName).collect(Collectors.joining(",")),
                gatewayRoutes
        );
        log.info("command - {} - already send", command);
    }
}
