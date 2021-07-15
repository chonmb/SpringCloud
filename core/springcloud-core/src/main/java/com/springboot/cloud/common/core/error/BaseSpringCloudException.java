package com.springboot.cloud.common.core.error;

import com.springboot.cloud.common.core.constant.ErrorConstant;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/13 16:21
 */

public abstract class BaseSpringCloudException extends RuntimeException {
    private String code;

    public BaseSpringCloudException(String message) {
        super(message);
    }

    public BaseSpringCloudException(String message, Throwable cause, String code) {
        super(message, cause);
        this.code = code;
    }

    public BaseSpringCloudException(String message, String code) {
        super(message);
        this.code = code;
    }

    public BaseSpringCloudException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    public BaseSpringCloudException(ErrorConstant errorConstant){
        super(errorConstant.getMessage());
        this.code=errorConstant.getCode();
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
