package com.lopy.common.utils;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.vo.RespVO;

import java.util.Locale;

public class ResponseUtil {

    private ResponseUtil() {}

    public static RespVO<Object> build(int code, String msg) {
        return RespVO.builder().code(code).message(msg).build();
    }

    public static RespVO<Object> buildFailure(Locale locale, String... args) {
        String msgCode = "resp.msg.failure";
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespVO<Object> buildUnAuthorized(Locale locale, String... args) {
        String msgCode = "resp.msg.unauthorized";
        return buildResp(CommonConstant.Code.UNAUTHORIZED, msgCode, locale, args);
    }

    public static RespVO<Object> buildForbidden(Locale locale, String... args) {
        String msgCode = "resp.msg.forbidden";
        return buildResp(CommonConstant.Code.FORBIDDEN, msgCode, locale, args);
    }

    public static RespVO<Object> buildNotFound(Locale locale, String... args) {
        String msgCode = "resp.msg.not.found";
        return buildResp(CommonConstant.Code.NOT_FOUND, msgCode, locale, args);
    }

    public static RespVO<Object> buildMethodNotSupport(Locale locale, String... args) {
        String msgCode = "resp.msg.method.not.support";
        return buildResp(CommonConstant.Code.METHOD_NOT_SUPPORT, msgCode, locale, args);
    }

    public static RespVO<Object> buildInternalServerError(Locale locale, String... args) {
        String msgCode = "resp.msg.internal.server.error";
        return buildResp(CommonConstant.Code.INTERNAL_SERVER_ERROR, msgCode, locale, args);
    }

    public static RespVO<Object> buildParamMiss(Locale locale, String... args) {
        String msgCode = "resp.msg.param.miss";
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespVO<Object> buildParamTypeError(Locale locale, String... args) {
        String msgCode = "resp.msg.param.type.error";
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespVO<Object> buildParamBindError(Locale locale, String msgCode, String... args) {
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespVO<Object> buildParamValid(Locale locale, String msgCode, String... args) {
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespVO<Object> buildResp(int code, String msgCode, Locale locale, String... args) {
        String message = MessageUtil.getMessage(msgCode, locale, args);
        return RespVO.builder().code(code).message(message).build();
    }
}
