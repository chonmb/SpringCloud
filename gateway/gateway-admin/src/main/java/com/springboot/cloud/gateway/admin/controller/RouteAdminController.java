package com.springboot.cloud.gateway.admin.controller;

import com.springboot.cloud.common.core.utils.Result;
import com.springboot.cloud.gateway.admin.service.IRouteAdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chonmb Email:jiangxin2@shanghai-electric.com
 * @date 2021/4/21 11:30
 */
@RestController
@RequestMapping("/api/gateway-admin")
public class RouteAdminController {
    private final IRouteAdminService routeAdminService;

    public RouteAdminController(IRouteAdminService routeAdminService) {
        this.routeAdminService = routeAdminService;
    }

    @GetMapping("/overload")
    public Result overload(){
        routeAdminService.overloadRoutes();
        return Result.success();
    }
}
