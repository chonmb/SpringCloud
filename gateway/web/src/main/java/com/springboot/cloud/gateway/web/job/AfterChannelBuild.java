package com.springboot.cloud.gateway.web.job;

import com.springboot.cloud.gateway.web.common.provider.GatewayAdminProvider;
import com.springboot.cloud.utils.rabbitmq.context.RabbitmqContextAware;
import org.springframework.stereotype.Component;


/**
 * @author chonmb
 * @email weichonmb@foxmail.com
 * @date 2021/7/15 10:01
 */
@Component
public class AfterChannelBuild implements RabbitmqContextAware {
    private final GatewayAdminProvider gatewayAdminProvider;

    public AfterChannelBuild(GatewayAdminProvider gatewayAdminProvider) {
        this.gatewayAdminProvider = gatewayAdminProvider;
    }

    @Override
    public void afterObserversRegistered() {
        gatewayAdminProvider.overload();
    }
}
