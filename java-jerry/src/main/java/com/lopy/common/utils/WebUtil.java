package com.lopy.common.utils;

import com.lopy.common.exception.ServiceException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class WebUtil {

    private static final ThreadLocal<String> requestBodyThreadLocal = new ThreadLocal<>();

    private WebUtil() {}

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        throw new ServiceException("WebUtil invokes exception, request Instance not found");
    }

    public static void setRequestMetaData(String data) {
        if (Objects.isNull(requestBodyThreadLocal.get())) {
            requestBodyThreadLocal.set(data);
        }
    }

    public static String getRequestMetaData() {
        String body = requestBodyThreadLocal.get();
        requestBodyThreadLocal.remove();
        return body;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = StringUtil.trim(request.getHeader("x-original-forwarded-for"));
        if (StringUtil.isNotBlank(ip)) {
            List<String> ipList = StringUtil.stringToList(ip, ",");
            if (CollectionUtil.isNotEmpty(ipList)) return StringUtil.trim(ipList.get(0));
        }
        return request.getRemoteAddr();
    }

    public static String getUserAgent() {
        HttpServletRequest request = getRequest();
        return StringUtil.trim(request.getHeader("User-Agent"));
    }
}
