package com.springboot.cloud.gateway.web.common.provider.fallback;

import com.springboot.cloud.common.core.utils.Result;
import com.springboot.cloud.gateway.web.common.provider.GatewayAdminProvider;
import org.springframework.stereotype.Component;

/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/15 9:53
 */
@Component
public class GatewayAdminProviderFallback implements GatewayAdminProvider {
    @Override
    public Result<Object> overload() {
        return Result.requestFail();
    }
}
