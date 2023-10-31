package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
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
@RequestMapping(CommonConstant.API.V1_PATH + "/auth")
public class AuthController {

    @Autowired
    private AuthService authServiceImpl;

    @PostMapping("/login/phone")
    public RespVO<Void> phoneLogin(@RequestBody LoginDTO loginDTO) {
        String token = authServiceImpl.phoneLogin(loginDTO);
        return RespVO.ok(token);
    }

    @PostMapping("/login/google")
    public RespVO<Void> googleLogin(@RequestBody LoginDTO loginDTO) {
        String token = authServiceImpl.googleLogin(loginDTO);
        return RespVO.ok(token);
    }

    @PostMapping("/login/apple")
    public RespVO<Void> appleLogin(@RequestBody LoginDTO loginDTO) {
        String token = authServiceImpl.appleLogin(loginDTO);
        return RespVO.ok(token);
    }
}
