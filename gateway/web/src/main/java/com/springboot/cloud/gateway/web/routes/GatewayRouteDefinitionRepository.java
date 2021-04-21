package com.springboot.cloud.gateway.web.routes;

import com.springboot.cloud.gateway.web.service.IRouteService;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 10:39
 */
@Component
public class GatewayRouteDefinitionRepository implements RouteDefinitionRepository {
    private final IRouteService routeService;

    public GatewayRouteDefinitionRepository(IRouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.fromIterable(routeService.getRouteDefinitions());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(routeDefinition -> {
            routeService.save(routeDefinition);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            routeService.delete(id);
            return Mono.empty();
        });
    }
}
