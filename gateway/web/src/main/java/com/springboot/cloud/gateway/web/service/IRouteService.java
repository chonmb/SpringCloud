package com.springboot.cloud.gateway.web.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;
import java.util.List;

/**
 * @author chonmb Email:jiangxin2@shanghai-electric.com
 * @date 2021/4/20 11:31
 */

public interface IRouteService {
    Collection<RouteDefinition> getRouteDefinitions();

    RouteDefinition save(RouteDefinition routeDefinition);

    RouteDefinition delete(String routeId);

    void save(List<RouteDefinition> routeDefinitionList);

    void delete(List<String> routeId);

    void clearAndSave(List<RouteDefinition> routeDefinitionList);
}
