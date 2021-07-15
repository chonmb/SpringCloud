package com.springboot.cloud.gateway.admin.common.mapper;

import com.springboot.cloud.common.core.models.RouteInfo;
import com.springboot.cloud.gateway.admin.models.entities.GatewayRoute;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/21 9:57
 */

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RouteConverter {
    /**
     * 对象转换
     * @param gatewayRoute 路由实体
     * @return 路由信息
     */
    RouteInfo toRouteInfo(GatewayRoute gatewayRoute);

    /**
     * 批量对象转换
     * @param gatewayRoutes
     * @return
     */
    List<RouteInfo> toRouteInfo(List<GatewayRoute> gatewayRoutes);
}
