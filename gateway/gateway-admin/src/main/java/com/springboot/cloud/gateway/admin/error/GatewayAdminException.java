package com.springboot.cloud.gateway.admin.error;

import com.springboot.cloud.common.core.constant.ErrorConstant;
import com.springboot.cloud.common.core.error.SpringCloudBasicException;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/21 9:13
 */

public class GatewayAdminException extends SpringCloudBasicException {
    public GatewayAdminException(ErrorConstant errorConstant) {
        super(errorConstant);
    }
}
