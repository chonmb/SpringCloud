package com.springboot.cloud.gateway.web.common.provider;

import com.springboot.cloud.common.core.utils.Result;
import com.springboot.cloud.gateway.web.common.provider.fallback.GatewayAdminProviderFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/15 9:44
 */
@Primary
@FeignClient(name = "gateway-admin",fallback = GatewayAdminProviderFallback.class)
public interface GatewayAdminProvider {
    /**
     * 路由重载
     * @return
     */
    @GetMapping(value = "/api/gateway-admin/overload")
    Result<Object> overload();
}
