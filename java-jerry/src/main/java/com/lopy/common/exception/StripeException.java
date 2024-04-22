package com.lopy.common.exception;

import com.lopy.common.constant.CommonConstant;
import lombok.Data;

@Data
public class StripeException extends RuntimeException {

    private final String message;
    private final int code;

    public StripeException(String message) {
        super(message);
        this.message = message;
        this.code = CommonConstant.Code.STRIPE_ERROR;
    }

    public StripeException(int code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public StripeException(int code, Throwable cause) {
        super(cause);
        this.message = cause.getMessage();
        this.code = code;
    }
}
