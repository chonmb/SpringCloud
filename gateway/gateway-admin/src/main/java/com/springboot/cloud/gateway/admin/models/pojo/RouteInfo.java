package com.springboot.cloud.gateway.admin.models.pojo;

import lombok.Data;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/20 15:56
 */
@Data
public class RouteInfo {
    String filters;
    String predicates;
    int ordered;
    String name;
    String uri;
}
