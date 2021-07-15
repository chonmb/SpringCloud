package com.springboot.cloud.gateway.admin.events;

import com.springboot.cloud.common.core.constant.CommonConstant;
import com.springboot.cloud.common.core.constant.RouteOperationConstant;
import com.springboot.cloud.common.core.models.RouteInfo;
import com.springboot.cloud.utils.rabbitmq.annotation.SendPublishRabbitmqMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/21 10:11
 */
@Component
@Slf4j
public class GatewayAdminEvent {

    private final RedisTemplate<String, RouteInfo> redisTemplate;

    public GatewayAdminEvent( RedisTemplate<String, RouteInfo> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @SendPublishRabbitmqMessage(channel=CommonConstant.GATEWAY_RABBITMQ_NAME)
    public String sendRouteCommand(RouteOperationConstant operation, String key, List<RouteInfo> routeInfo) {
        if (routeInfo!=null&&!routeInfo.isEmpty()){
            routeInfo.forEach(routeInfo1 -> redisTemplate.opsForValue().set(CommonConstant.GATEWAY_REDIS_PREFIX + key, routeInfo1));
        }
        return operation.name() + ":" + key;
    }
}
