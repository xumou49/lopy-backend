package com.lopy.service.intf;

import com.lopy.common.dto.auth.LoginDTO;

public interface AuthService {

    String phoneLogin(LoginDTO loginDTO);
    String googleLogin(LoginDTO loginDTO);
    String appleLogin(LoginDTO loginDTO);
}
