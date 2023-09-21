package com.lopy.controller;

import com.lopy.common.dto.RespDTO;
import com.lopy.common.dto.auth.LoginForm;
import com.lopy.service.intf.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth API")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authServiceImpl;

    @PostMapping("/login/phone")
    public RespDTO<String> phoneLogin(@RequestBody LoginForm loginForm) {
        String token = authServiceImpl.phoneLogin(loginForm);
        return new RespDTO<>(token);
    }

    @PostMapping("/login/google")
    public RespDTO<String> googleLogin(@RequestBody LoginForm loginForm) {
        String token = authServiceImpl.googleLogin(loginForm);
        return new RespDTO<>(token);
    }

    @PostMapping("/login/apple")
    public RespDTO<String> appleLogin(@RequestBody LoginForm loginForm) {
        String token = authServiceImpl.appleLogin(loginForm);
        return new RespDTO<>(token);
    }
}
