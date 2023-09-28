package com.lopy.controller;

import com.lopy.common.dto.auth.LoginDTO;
import com.lopy.common.vo.RespVO;
import com.lopy.service.biz.intf.AuthService;
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
    public RespVO<String> phoneLogin(@RequestBody LoginDTO loginDTO) {
        String token = authServiceImpl.phoneLogin(loginDTO);
        return new RespVO<>(token);
    }

    @PostMapping("/login/google")
    public RespVO<String> googleLogin(@RequestBody LoginDTO loginDTO) {
        String token = authServiceImpl.googleLogin(loginDTO);
        return new RespVO<>(token);
    }

    @PostMapping("/login/apple")
    public RespVO<String> appleLogin(@RequestBody LoginDTO loginDTO) {
        String token = authServiceImpl.appleLogin(loginDTO);
        return new RespVO<>(token);
    }
}
