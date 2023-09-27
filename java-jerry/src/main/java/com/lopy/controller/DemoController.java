package com.lopy.controller;

import com.lopy.common.auth.Authorize;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.RespDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Demo API")
@RequestMapping("/api/v1/demo")
@RestController
public class DemoController {

    @PostMapping("/list")
    @Authorize(access = {CommonConstant.Account.RESTAURATEUR, CommonConstant.Account.CUSTOMER})
    public RespDTO<Void> list() {
        System.out.println("in role list");
        return new RespDTO<>();
    }

    @PostMapping("/page")
    @Authorize(access = {CommonConstant.Account.RESTAURATEUR, CommonConstant.Account.CUSTOMER})
    public RespDTO<Void> page() {
        System.out.println("in role page");
        return new RespDTO<>();
    }

    @PutMapping("/create")
    @Authorize(access = CommonConstant.Account.RESTAURATEUR)
    public RespDTO<Void> create() {
        System.out.println("in role create");
        return new RespDTO<>();
    }

    @PutMapping("/modify")
    @Authorize(access = CommonConstant.Account.RESTAURATEUR)
    public RespDTO<Void> modify() {
        return new RespDTO<>();
    }

    @DeleteMapping("/delete")
    @Authorize(access = CommonConstant.Account.RESTAURATEUR)
    public RespDTO<Void> delete() {
        return new RespDTO<>();
    }
}
