package com.lopy.common.exception;

import com.lopy.common.constant.CommonConstant;
import lombok.Data;

@Data
public class ServiceException extends RuntimeException {

    private final String message;
    private final int code;

    public ServiceException(String message) {
        super(message);
        this.message = message;
        this.code = CommonConstant.Code.INTERNAL_SERVER_ERROR;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public ServiceException(int code, Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
        this.code = code;
    }
}
