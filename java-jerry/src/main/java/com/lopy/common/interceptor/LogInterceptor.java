package com.lopy.common.interceptor;

import com.lopy.common.request.RequestWrapper;
import com.lopy.common.utils.JsonUtil;
import com.lopy.common.utils.WebUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    /**
     * @description Log the user action
     * @author Dex
     * @date 2023/09/24
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        writeBodyToLocalThread(request);
        return true;
    }

    private void writeBodyToLocalThread(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuilder paramString = new StringBuilder();
        parameterMap.forEach((k, arr) -> {
            paramString.append(k).append("=");
            paramString.append(StringUtils.join(arr, ",")).append("&");
        });

        String bodyString =  new RequestWrapper(request).getBodyString(request);
        Map<String, String> map = new HashMap<>();
        map.put("args", paramString.toString());
        map.put("body", bodyString);
        WebUtil.setRequestMetaData(JsonUtil.toJson(map));
    }
}
