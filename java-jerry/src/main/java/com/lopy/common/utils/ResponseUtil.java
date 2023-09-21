package com.lopy.common.utils;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.RespDTO;

import java.util.Locale;

public class ResponseUtil {

    private ResponseUtil() {}

    public static RespDTO<Object> build(int code, String msg) {
        return RespDTO.builder().code(code).message(msg).build();
    }

    public static RespDTO<Object> buildFailure(Locale locale, String... args) {
        String msgCode = "resp.msg.failure";
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespDTO<Object> buildUnAuthorized(Locale locale, String... args) {
        String msgCode = "resp.msg.unauthorized";
        return buildResp(CommonConstant.Code.UNAUTHORIZED, msgCode, locale, args);
    }

    public static RespDTO<Object> buildForbidden(Locale locale, String... args) {
        String msgCode = "resp.msg.forbidden";
        return buildResp(CommonConstant.Code.FORBIDDEN, msgCode, locale, args);
    }

    public static RespDTO<Object> buildNotFound(Locale locale, String... args) {
        String msgCode = "resp.msg.not.found";
        return buildResp(CommonConstant.Code.NOT_FOUND, msgCode, locale, args);
    }

    public static RespDTO<Object> buildMethodNotSupport(Locale locale, String... args) {
        String msgCode = "resp.msg.method.not.support";
        return buildResp(CommonConstant.Code.METHOD_NOT_SUPPORT, msgCode, locale, args);
    }

    public static RespDTO<Object> buildInternalServerError(Locale locale, String... args) {
        String msgCode = "resp.msg.internal.server.error";
        return buildResp(CommonConstant.Code.INTERNAL_SERVER_ERROR, msgCode, locale, args);
    }

    public static RespDTO<Object> buildParamMiss(Locale locale, String... args) {
        String msgCode = "resp.msg.param.miss";
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespDTO<Object> buildParamTypeError(Locale locale, String... args) {
        String msgCode = "resp.msg.param.type.error";
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespDTO<Object> buildParamBindError(Locale locale, String msgCode, String... args) {
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespDTO<Object> buildParamValid(Locale locale, String msgCode, String... args) {
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespDTO<Object> buildResp(int code, String msgCode, Locale locale, String... args) {
        String message = MessageUtil.getMessage(msgCode, locale, args);
        return RespDTO.builder().code(code).message(message).build();
    }
}
