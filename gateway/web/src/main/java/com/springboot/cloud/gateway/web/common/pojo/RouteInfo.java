package com.springboot.cloud.gateway.web.common.pojo;

import lombok.Data;

/**
 * @author chonmb Email:jiangxin2@shanghai-electric.com
 * @date 2021/4/20 15:56
 */
@Data
public class RouteInfo {
    String filters;
    String predicates;
    int ordered;
    String routeName;
    String uri;
}
