package com.lopy.service.intf;

import com.lopy.common.dto.auth.LoginForm;

public interface AuthService {

    String phoneLogin(LoginForm loginForm);
    String googleLogin(LoginForm loginForm);
    String appleLogin(LoginForm loginForm);
}
