package com.lopy.service;

import com.lopy.LopyApplication;
import com.lopy.common.dto.auth.LoginDTO;
import com.lopy.service.biz.intf.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LopyApplication.class)
class TestAuthService {

    @Autowired
    private AuthService authServiceImpl;

    @Test
    void testGoogleLogin() {
        String token = "ya29.a0AfB_byALrm0QqvNHRpBJkaTj3jFqJaU-ccfoxRooq06lsfAnwc1NXOx0rTGmE3Q65D_7ImCeYMIrHQAy1c6asvXZ7arN_DSSbKMImbsVxm-SeAZaR8C_ogteWJog8fwSdF-GYKjcaVu4jv3JVO7pkEuCESnGLNyY6BENaCgYKAT0SARMSFQGOcNnC8kjEtBjoxEwxOABaCbcv-A0171";
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setToken(token);
        String s = authServiceImpl.googleLogin(loginDTO);
        System.out.println(s);
    }

    @Test
    void testAppleLogin() {
        String token = "";
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setToken(token);
        String s = authServiceImpl.appleLogin(loginDTO);
    }
}
