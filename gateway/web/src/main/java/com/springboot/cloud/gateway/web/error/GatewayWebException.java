package com.springboot.cloud.gateway.web.error;

import com.springboot.cloud.common.core.constant.ErrorConstant;
import com.springboot.cloud.common.core.error.SpringCloudBasicException;

/**
 * @author chonmb Email:jiangxin2@shanghai-electric.com
 * @date 2021/4/20 10:19
 */

public class GatewayWebException extends SpringCloudBasicException {
    public GatewayWebException(String message) {
        super(message);
    }

    public GatewayWebException(ErrorConstant errorConstant) {
        super(errorConstant);
    }
}
