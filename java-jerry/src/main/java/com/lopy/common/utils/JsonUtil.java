package com.lopy.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.vo.RespVO;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@Slf4j
public class JsonUtil {

    private JsonUtil() {
    }

    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T fromJson(String json, Class<T> clz) {
        return JSON.parseObject(json, clz);
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> elementClazz) { return StringUtil.isBlank(json)?
            Collections.emptyList():JSON.parseArray(json, elementClazz); }

    public static <T> T fromJsonToRefType(String json, TypeReference<T> typeReference) { return JSON.parseObject(json, typeReference); }

    public static <T> RespVO<PageResult<T>> parseRespPageResult(String json, Class<T> clazz) {
        ParameterizedTypeImpl inner = new ParameterizedTypeImpl(new Type[]{clazz}, null, PageResult.class);
        ParameterizedTypeImpl outer = new ParameterizedTypeImpl(new Type[]{inner}, null, RespVO.class);
        return JSON.parseObject(json, outer);
    }

    public static <T> RespVO<List<T>> parseRespListResult(String json, Class<T> clazz) {
        ParameterizedTypeImpl inner = new ParameterizedTypeImpl(new Type[]{clazz}, null, List.class);
        ParameterizedTypeImpl outer = new ParameterizedTypeImpl(new Type[]{inner}, null, RespVO.class);
        return JSON.parseObject(json, outer);
    }

    public static <T> RespVO<T> parseRespObject(String json, Class<T> clazz) {
        ParameterizedTypeImpl type = new ParameterizedTypeImpl(new Type[]{clazz}, null, RespVO.class);
        return JSON.parseObject(json, type);
    }

    public static JSONObject fromJson(String json) {
        return JSON.parseObject(json);
    }
}
