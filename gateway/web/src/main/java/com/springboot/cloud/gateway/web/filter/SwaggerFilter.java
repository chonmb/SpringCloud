package com.springboot.cloud.gateway.web.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author chonmb Email:jiangxin2@shanghai-electric.com
 * @date 2021/4/20 10:27
 */

@Component
public class SwaggerFilter implements GlobalFilter, Ordered {
    private static final String SWAGGER_PATH_REGEX = "/api/.*/v2/api-docs";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (exchange.getRequest().getPath().value().matches(SWAGGER_PATH_REGEX)) {
            chain.filter(exchange);
        }
        return unauthorized(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }
}
