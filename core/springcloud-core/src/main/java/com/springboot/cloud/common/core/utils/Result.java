package com.springboot.cloud.common.core.utils;

import com.springboot.cloud.common.core.constant.ErrorConstant;
import com.springboot.cloud.common.core.error.SpringCloudBasicException;

import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * @author chonmb Email:weichonmb@foxmail.com
 * @date 2021/4/13 17:13
 */

public class Result<T> {
    public static final String SUCCESSFUL_CODE = "200";
    public static final String SUCCESSFUL_MESSAGE = "处理成功";

    private String code;
    private String message;
    private Instant time;
    private T data;

    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorConstant errorConstant) {
        this.code = errorConstant.getCode();
        this.message = errorConstant.getMessage();
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(ErrorConstant errorConstant, T data) {
        this(errorConstant);
        this.data = data;
    }

    private Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.time = ZonedDateTime.now().toInstant();
    }

    public static Result<Object> success(Object data) {
        return new Result<>(SUCCESSFUL_CODE, SUCCESSFUL_MESSAGE, data);
    }

    public static Result<Object> success() {
        return success(null);
    }

    public static Result<Object> fail() {
        return new Result<>(ErrorConstant.SYSTEM_ERROR);
    }

    public static Result<Object> fail(SpringCloudBasicException springCloudBasicException, Object data) {
        return new Result<>(springCloudBasicException.getCode(), springCloudBasicException.getMessage(), data);
    }

    public static Result<Object> fail(SpringCloudBasicException springCloudBasicException) {
        return fail(springCloudBasicException, null);
    }

    public static Result<Object> fail(ErrorConstant errorConstant, Object data) {
        return new Result<>(errorConstant, data);
    }

    public static Result<Object> fail(ErrorConstant errorConstant) {
        return Result.fail(errorConstant, null);
    }

    public static Result<Object> fail(Object data) {
        return Result.fail(ErrorConstant.SYSTEM_ERROR, data);
    }

    public boolean isSuccess() {
        return SUCCESSFUL_CODE.equals(this.code);
    }

    public boolean isFail() {
        return !isSuccess();
    }
}
