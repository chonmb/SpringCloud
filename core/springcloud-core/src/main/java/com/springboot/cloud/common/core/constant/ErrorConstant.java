package com.springboot.cloud.common.core.constant;

/**
 * @author chonmb Email:jiangxin2@shanghai-electric.com
 * @date 2021/4/13 10:38
 */

public enum ErrorConstant {
    //
    SYSTEM_ERROR("-1","系统错误");

    String code;
    String message;

    ErrorConstant(String code,String message){
        this.code=code;
        this.message=message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
