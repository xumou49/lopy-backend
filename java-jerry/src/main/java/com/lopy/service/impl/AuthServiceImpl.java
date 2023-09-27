package com.lopy.service.impl;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.auth.LoginForm;
import com.lopy.common.exception.ServiceException;
import com.lopy.common.utils.JWTUtil;
import com.lopy.common.utils.MessageUtil;
import com.lopy.common.utils.StringUtil;
import com.lopy.service.intf.AuthService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String phoneLogin(LoginForm loginForm) {
        return createJWT();
    }

    @Override
    public String googleLogin(LoginForm loginForm) {
        return createJWT();
    }

    @Override
    public String appleLogin(LoginForm loginForm) {
        return createJWT();
    }

    private String createJWT() {
        Map<String, String> claims = Map.of("userId", "1", "userType", CommonConstant.Account.CUSTOMER);
        String token = JWTUtil.createToken(claims);
        if (StringUtil.isBlank(token)) {
            throw new ServiceException(MessageUtil.getMessage("error.token.create"));
        }
        return token;
    }
}
