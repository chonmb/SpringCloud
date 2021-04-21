package com.springboot.cloud.gateway.web.common.pojo;

import com.springboot.cloud.common.core.constant.RouteOperationConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 13:50
 */
@Getter
@Setter
public class RouteCommand {
    private RouteOperationConstant operation;
    private List<String> routeId;
    private List<RouteDefinition> routeDefinition;
}
