package com.springboot.cloud.gateway.web.common.factory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.springboot.cloud.common.core.constant.CommonConstant;
import com.springboot.cloud.common.core.constant.ErrorConstant;
import com.springboot.cloud.common.core.constant.RouteOperationConstant;
import com.springboot.cloud.common.core.models.RouteInfo;
import com.springboot.cloud.gateway.web.models.pojo.RouteCommand;
import com.springboot.cloud.gateway.web.error.GatewayWebException;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 13:48
 */

@Component
public class RouteCommandFactory {
    private static final int ROUTE_COMMAND_DEFAULT_LENGTH = 2;
    private final RedisTemplate<String, RouteInfo> redisTemplate;
    private final Gson gson;

    public RouteCommandFactory(RedisTemplate<String, RouteInfo> redisTemplate) {
        this.redisTemplate = redisTemplate;
        gson = new Gson();
    }

    public RouteCommand buildCommand(@NonNull String message) {
        String[] commands = message.split(":");
        if (commands.length != ROUTE_COMMAND_DEFAULT_LENGTH) {
            throw new GatewayWebException(ErrorConstant.ILLEGAL_ROUTE_COMMAND_PARAM);
        }
        return buildRouteCommand(commands[0], commands[1]);
    }

    private RouteCommand buildRouteCommand(@NonNull String operation, @NonNull String routeId) {
        RouteCommand routeCommand = new RouteCommand();
        routeCommand.setOperation(RouteOperationConstant.valueOf(operation));
        routeCommand.setRouteId(Arrays.asList(routeId.split(",")));
        routeCommand.setRouteDefinition(getRouteDefinitionFromRedis(routeCommand.getRouteId()));
        return routeCommand;
    }

    private List<RouteDefinition> getRouteDefinitionFromRedis(List<String> routeIds) {
        return routeIds.stream().map(this::getRouteDefinitionFromRedis).collect(Collectors.toList());
    }

    private RouteDefinition getRouteDefinitionFromRedis(String routeId) {
        RouteInfo routeInfo = redisTemplate.opsForValue().get(CommonConstant.GATEWAY_REDIS_PREFIX + routeId);
        Optional.ofNullable(routeInfo).orElseThrow(() -> new GatewayWebException(ErrorConstant.FAILED_TO_FIND_ROUTE_INFO));
        try {
            RouteDefinition routeDefinition = new RouteDefinition();
            routeDefinition.setUri(new URI(routeInfo.getUri()));
            routeDefinition.setId(routeId);
            routeDefinition.setOrder(routeInfo.getOrdered());
            List<String> predicates = gson.fromJson(routeInfo.getPredicates(), new TypeToken<List<String>>() {
            }.getType());
            routeDefinition.setPredicates(predicates.stream().map(PredicateDefinition::new).collect(Collectors.toList()));
            List<String> filters = gson.fromJson(routeInfo.getFilters(), new TypeToken<List<String>>() {
            }.getType());
            routeDefinition.setFilters(filters.stream().map(FilterDefinition::new).collect(Collectors.toList()));
            return routeDefinition;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
