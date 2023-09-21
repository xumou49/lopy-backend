package com.lopy.common.exception;

import com.lopy.common.auth.AuthContext;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.RespDTO;
import com.lopy.common.utils.MessageUtil;
import com.lopy.common.utils.ResponseUtil;
import com.lopy.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @description Convert exception message to readable content
 * @author Dex
 * @date 2023/09/21
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RespDTO<Object> handleException(MissingServletRequestParameterException e) {
        log.warn("Missing Request Parameter", e);
        Locale locale = AuthContext.getUserLocale();
        return ResponseUtil.buildParamMiss(locale, e.getParameterName());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public RespDTO<Object> handleException(MethodArgumentTypeMismatchException e) {
        Locale locale = AuthContext.getUserLocale();
        return ResponseUtil.buildParamTypeError(locale, e.getName());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RespDTO<Object> handleException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        return convertFieldError(result.getFieldErrors());
    }

    @ExceptionHandler(BindException.class)
    public RespDTO<Object> handleException(BindException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        return convertFieldError(fieldErrors);
    }

    private RespDTO<Object> convertFieldError(List<FieldError> fieldErrors) {
        Locale locale = AuthContext.getUserLocale();
        List<String> errMessages = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            String field = MessageUtil.getMessage(String.format("%s.%s", fieldError.getObjectName(), fieldError.getField()), locale);
            errMessages.add(MessageUtil.getMessage(fieldError.getDefaultMessage(), locale, field));
        }
        return ResponseUtil.build(HttpServletResponse.SC_BAD_REQUEST, StringUtil.listToString(errMessages, ";"));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public RespDTO<Object> handleException(NoHandlerFoundException e) {
        log.error("404 Not Found", e);
        Locale locale = AuthContext.getUserLocale();
        return ResponseUtil.buildNotFound(locale, e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public RespDTO<Object> handleException(HttpRequestMethodNotSupportedException e) {
        log.error("Request Method Not Supported", e);
        Locale locale = AuthContext.getUserLocale();
        return ResponseUtil.buildMethodNotSupport(locale, e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public RespDTO<Object> handleException(ServiceException e) {
        log.error("Service Exception", e);
        return ResponseUtil.build(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public RespDTO<Object> handleException(ValidationException e) {
        return ResponseUtil.build(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public RespDTO<Object> handleException(PermissionDeniedException e) {
        log.error("Permission Denied", e);
        Locale locale = AuthContext.getUserLocale();
        return ResponseUtil.buildResp(e.getCode(), e.getMessage(), locale);
    }

    @ExceptionHandler(Throwable.class)
    public RespDTO<Object> handleException(Throwable e) {
        log.error("Internal Server Error", e);
        return ResponseUtil.build(CommonConstant.Code.INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
