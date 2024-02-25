package com.lopy.common.interceptor;

import com.lopy.common.constant.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    // @Autowired
    // private RedisTemplate<String, String> redisSessionTemplate;

    private Set<String> passUrls = new HashSet<>();

    @PostConstruct
    private void initPassUrl() {
        passUrls.add("/client/");
    }

    /**
     * @description Session validator, check if user is logged in before accessing the resource
     * @author Dex
     * @date 2023/09/21
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("In session pre handler ...");
        log.info("request uri: {}", request.getRequestURI());

        // AntPathMatcher pathMatcher = new AntPathMatcher();
        // check if user is logged in
        // String token = request.getHeader(AuthConstant.CURRENT_AUTH_TOKEN_HEADER);
        // if (StringUtil.isBlank(token)) {
        //     // TODO
        //     throw new PermissionDeniedException("error");
        // }
        //
        // JWTPayload payload = JWTUtil.verifyToken(token);
        // log.info("payload: {}", payload);
        // if (payload == null) {
        //     // TODO
        //     throw new PermissionDeniedException("error");
        // }

        // take the id from request header for now (todo, will change in the later stage)
        // request.setAttribute(AuthConstant.CURRENT_USER_HEADER, payload.getUserId());
        request.setAttribute(AuthConstant.CURRENT_USER_HEADER, request.getHeader(AuthConstant.CURRENT_USER_HEADER));
        // request.setAttribute(AuthConstant.CURRENT_USER_TYPE_HEADER, payload.getUserType());
        return true;
    }

}
