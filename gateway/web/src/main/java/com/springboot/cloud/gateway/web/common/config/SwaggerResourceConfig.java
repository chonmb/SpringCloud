package com.springboot.cloud.gateway.web.common.config;

import com.springboot.cloud.gateway.web.service.IRouteService;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 路由聚合
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 13:12
 */
@Component
@Primary
public class SwaggerResourceConfig implements SwaggerResourcesProvider {
    private final IRouteService routeService;

    public SwaggerResourceConfig(IRouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public List<SwaggerResource> get() {
        return routeService.getRouteDefinitions().stream()
                .map(gatewayRoute -> {
                    String prefix = gatewayRoute.getPredicates().get(0).getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0");
                    if (!StringUtils.isEmpty(prefix)) {
                        SwaggerResource swaggerResource = new SwaggerResource();
                        swaggerResource.setName(gatewayRoute.getId());
                        swaggerResource.setUrl(prefix.replace("**", "v2/api-docs"));
                        swaggerResource.setSwaggerVersion("1.0");
                        return swaggerResource;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
