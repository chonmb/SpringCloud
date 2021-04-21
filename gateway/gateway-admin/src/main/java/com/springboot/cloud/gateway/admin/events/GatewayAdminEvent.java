package com.springboot.cloud.gateway.admin.events;

import com.rabbitmq.client.Channel;
import com.springboot.cloud.common.core.constant.CommonConstant;
import com.springboot.cloud.common.core.constant.RouteOperationConstant;
import com.springboot.cloud.gateway.admin.models.pojo.RouteInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import sun.security.util.ArrayUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/21 10:11
 */
@Component
@Slf4j
public class GatewayAdminEvent {
    private final Channel channel;
    private final RedisTemplate<String, RouteInfo> redisTemplate;

    public GatewayAdminEvent(Channel channel, RedisTemplate<String, RouteInfo> redisTemplate) {
        this.channel = channel;
        this.redisTemplate = redisTemplate;
    }

    private void sendRabbitmqMessage(String message) {
        try {
            channel.basicPublish("", CommonConstant.GATEWAY_RABBITMQ_NAME, null, message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRouteCommand(RouteOperationConstant operation, String key, List<RouteInfo> routeInfo) {
        if (routeInfo!=null&&!routeInfo.isEmpty()){
            routeInfo.forEach(routeInfo1 -> redisTemplate.opsForValue().set(CommonConstant.GATEWAY_REDIS_PREFIX + key, routeInfo1));
        }
        sendRabbitmqMessage(operation.name() + ":" + key);
    }
}
