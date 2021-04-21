package com.springboot.cloud.gateway.web.service.impl;

import com.springboot.cloud.common.core.constant.ErrorConstant;
import com.springboot.cloud.gateway.web.error.GatewayWebException;
import com.springboot.cloud.gateway.web.service.IRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 11:33
 */
@Service
@Slf4j
public class RouteServiceImpl implements IRouteService {
    private final Map<String, RouteDefinition> routeDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public Collection<RouteDefinition> getRouteDefinitions() {
        return routeDefinitionMap.values();
    }

    @Override
    public RouteDefinition save(@NonNull RouteDefinition routeDefinition) {
        RouteDefinition route = routeDefinitionMap.put(routeDefinition.getId(), routeDefinition);
        log.info("新增路由1条：{},目前路由共{}条", routeDefinition, routeDefinitionMap.size());
        return route;
    }

    @Override
    public RouteDefinition delete(String routeId) {
        RouteDefinition route = routeDefinitionMap.remove(routeId);
        Optional.ofNullable(route).orElseThrow(() -> new GatewayWebException(ErrorConstant.FAILED_TO_FIND_ROUTES));
        log.info("删除路由1条：{},目前路由共{}条", routeId, routeDefinitionMap.size());
        return route;
    }

    @Override
    public void save(List<RouteDefinition> routeDefinitionList) {
        routeDefinitionList.forEach(this::save);
    }

    @Override
    public void delete(List<String> routeId) {
        routeId.forEach(this::delete);
    }

    @Override
    public void clearAndSave(List<RouteDefinition> routeDefinitionList) {
        routeDefinitionMap.clear();
        save(routeDefinitionList);
    }
}
