package com.springboot.cloud.gateway.admin.common.mapper;

import com.springboot.cloud.gateway.admin.models.entities.GatewayRoute;
import com.springboot.cloud.gateway.admin.models.pojo.RouteInfo;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

/**
 * @author chonmb Email:jiangxin2@shanghai-electric.com
 * @date 2021/4/21 9:57
 */

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RouteConverter {
    RouteInfo toRouteInfo(GatewayRoute gatewayRoute);

    List<RouteInfo> toRouteInfo(List<GatewayRoute> gatewayRoutes);
}
