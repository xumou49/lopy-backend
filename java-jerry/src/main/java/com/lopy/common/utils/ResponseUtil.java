package com.lopy.common.utils;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.vo.RespVO;

import java.util.Locale;

public class ResponseUtil {

    private ResponseUtil() {}

    public static RespVO build(String msg) {
        return RespVO.ok(msg);
    }

    public static RespVO build(int code, String msg) {
        return RespVO.error(code, msg);
    }

    public static RespVO buildFailure(Locale locale, String... args) {
        String msgCode = "resp.msg.failure";
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespVO buildUnAuthorized(Locale locale, String... args) {
        String msgCode = "resp.msg.unauthorized";
        return buildResp(CommonConstant.Code.UNAUTHORIZED, msgCode, locale, args);
    }

    public static RespVO buildForbidden(Locale locale, String... args) {
        String msgCode = "resp.msg.forbidden";
        return buildResp(CommonConstant.Code.FORBIDDEN, msgCode, locale, args);
    }

    public static RespVO buildNotFound(Locale locale, String... args) {
        String msgCode = "resp.msg.not.found";
        return buildResp(CommonConstant.Code.NOT_FOUND, msgCode, locale, args);
    }

    public static RespVO buildMethodNotSupport(Locale locale, String... args) {
        String msgCode = "resp.msg.method.not.support";
        return buildResp(CommonConstant.Code.METHOD_NOT_SUPPORT, msgCode, locale, args);
    }

    public static RespVO buildInternalServerError(Locale locale, String... args) {
        String msgCode = "resp.msg.internal.server.error";
        return buildResp(CommonConstant.Code.INTERNAL_SERVER_ERROR, msgCode, locale, args);
    }

    public static RespVO buildParamMiss(Locale locale, String... args) {
        String msgCode = "resp.msg.param.miss";
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespVO buildParamTypeError(Locale locale, String... args) {
        String msgCode = "resp.msg.param.type.error";
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespVO buildParamBindError(Locale locale, String msgCode, String... args) {
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespVO buildParamInvalid(Locale locale, String msgCode, String... args) {
        return buildResp(CommonConstant.Code.BAD_REQUEST, msgCode, locale, args);
    }

    public static RespVO buildResp(int code, String msgCode, Locale locale, String... args) {
        String message = MessageUtil.getMessage(msgCode, locale, args);
        return RespVO.error(code, message);
    }
    public static RespVO buildResp(String msgCode, Locale locale, String... args) {
        String message = MessageUtil.getMessage(msgCode, locale, args);
        return RespVO.ok( message);
    }
}
