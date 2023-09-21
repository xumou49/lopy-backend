package com.lopy.common.exception;

import com.lopy.common.constant.CommonConstant;
import lombok.Data;

@Data
public class ValidationException extends RuntimeException {

    private final String message;
    private final int code;

    public ValidationException(String message) {
        super(message);
        this.message = message;
        this.code = CommonConstant.Code.BAD_REQUEST;
    }

    public ValidationException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public ValidationException(int code, Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
        this.code = code;
    }
}
