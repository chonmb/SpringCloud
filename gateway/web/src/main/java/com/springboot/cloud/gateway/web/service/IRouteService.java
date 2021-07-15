package com.springboot.cloud.gateway.web.service;

import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collection;
import java.util.List;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 11:31
 */

public interface IRouteService {
    /**
     * 获取路由
     * @return 路由信息
     */
    Collection<RouteDefinition> getRouteDefinitions();

    /**
     * 新增路由
     * @param routeDefinition 路由信息
     * @return 路由信息
     */
    RouteDefinition save(RouteDefinition routeDefinition);

    /**
     * 删除路由
     * @param routeId 路由id
     * @return 路由信息
     */
    RouteDefinition delete(String routeId);

    /**
     * 新增路由（批量）
     * @param routeDefinitionList 路由信息
     */
    void save(List<RouteDefinition> routeDefinitionList);

    /**
     * 删除路由(批量）
     * @param routeId 路由id
     */
    void delete(List<String> routeId);

    /**
     * 路由重载
     * @param routeDefinitionList 路由信息
     */
    void clearAndSave(List<RouteDefinition> routeDefinitionList);
}
