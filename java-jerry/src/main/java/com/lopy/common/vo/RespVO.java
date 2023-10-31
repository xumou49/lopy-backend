package com.lopy.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.http.HttpStatus;

@Data
public class RespVO<T> {

    private String msg = "success";
    private int code = 200;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private T data;

    public static RespVO<Void> error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
    }

    public static RespVO<Void> error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static RespVO<Void> error(int code, String msg) {
        RespVO<Void> respVO = new RespVO<>();
        respVO.setCode(code);
        respVO.setMsg(msg);
        return respVO;
    }

    public static RespVO<Void> ok(String msg) {
        RespVO<Void> respVO = new RespVO<>();
        respVO.setMsg(msg);
        return respVO;
    }

    public static <T> RespVO<T> ok(T data) {
        RespVO<T> respVO = new RespVO<>();
        respVO.setData(data);
        return respVO;
    }

    public static RespVO<Void> ok() {
        return new RespVO<>();
    }
}
