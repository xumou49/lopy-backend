package com.lopy.common.interceptor;

import com.lopy.common.constant.AuthConstant;
import com.lopy.common.pojo.JWTPayload;
import com.lopy.common.utils.JWTUtil;
import com.lopy.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class RestaurantApiInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // copy the request token to the attribute
        String token = StringUtil.trim(request.getHeader(AuthConstant.CURRENT_AUTH_TOKEN_HEADER));

        // if token is not present, simply pass through the request
        if (StringUtil.isEmpty(token)) {
            return false;
        }

        // if token is present, parse it and copy the user id to the attribute
        // it is ok to pass through the request if the token is valid
        JWTPayload payload = JWTUtil.verifyToken(token);
        if (payload == null) {
            return false;
        }

        request.setAttribute(AuthConstant.CURRENT_USER_HEADER, payload.getUserId());
        request.setAttribute(AuthConstant.CURRENT_USER_TYPE_HEADER, payload.getUserType());
        return true;
    }

}
