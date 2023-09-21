package com.lopy.common.exception;

import com.lopy.common.constant.CommonConstant;
import lombok.Data;

@Data
public class PermissionDeniedException extends RuntimeException {

    private final String message;
    private final int code;

    public PermissionDeniedException(String message) {
        super(message);
        this.message = message;
        this.code = CommonConstant.Code.UNAUTHORIZED;
    }
}
