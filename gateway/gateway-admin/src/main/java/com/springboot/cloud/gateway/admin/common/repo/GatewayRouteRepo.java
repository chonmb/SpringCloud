package com.springboot.cloud.gateway.admin.common.repo;

import com.springboot.cloud.gateway.admin.models.entities.GatewayRoute;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chonmb Email:jiangxin2@shanghai-electric.com
 * @date 2021/4/21 11:12
 */

public interface GatewayRouteRepo extends JpaRepository<GatewayRoute,Long> {
}
