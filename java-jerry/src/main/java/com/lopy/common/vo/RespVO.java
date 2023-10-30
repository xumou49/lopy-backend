package com.lopy.common.vo;
import org.apache.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

public class RespVO extends HashMap<String, Object> {
    public RespVO() {
        put("code", 200);
        put("msg", "success");
    }

    public static RespVO error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
    }

    public static RespVO error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static RespVO error(int code, String msg) {
        RespVO r = new RespVO();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static RespVO ok(String msg) {
        RespVO r = new RespVO();
        r.put("msg", msg);
        return r;
    }

    public static RespVO ok(Map<String, Object> map) {
        RespVO r = new RespVO();
        r.putAll(map);
        return r;
    }

    public static RespVO ok() {
        return new RespVO();
    }

    public RespVO put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
