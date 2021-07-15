package com.springboot.cloud.common.core.constant;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/13 10:38
 */

public enum ErrorConstant {
    //
    FAILED_GET_RESPONSE("00001","请求失败"),

    FAILED_TO_FIND_ROUTES("A0001","没有找到路由"),
    ILLEGAL_ROUTE_COMMAND_PARAM("A0002","路由命令参数非法"),
    FAILED_TO_FIND_ROUTE_INFO ("A0003","加载路由信息失败"),

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
