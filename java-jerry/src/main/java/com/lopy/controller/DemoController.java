package com.lopy.controller;

import com.lopy.common.auth.Authorize;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.vo.RespVO;
import com.lopy.service.biz.intf.AppleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Demo API")
@RequestMapping("/api/v1/demo")
@RestController
public class DemoController {

    @Autowired
    private AppleService appleServiceImpl;

    @PostMapping("/list")
    @Authorize(access = {CommonConstant.Account.RESTAURATEUR, CommonConstant.Account.CUSTOMER})
    public RespVO<Void> list() {
        System.out.println("in role list");
        return new RespVO<>();
    }

    @PostMapping("/page")
    @Authorize(access = {CommonConstant.Account.RESTAURATEUR, CommonConstant.Account.CUSTOMER})
    public RespVO<Void> page() {
        System.out.println("in role page");
        return new RespVO<>();
    }

    @PutMapping("/create")
    @Authorize(access = CommonConstant.Account.RESTAURATEUR)
    public RespVO<Void> create() {
        System.out.println("in role create");
        return new RespVO<>();
    }

    @PutMapping("/modify")
    @Authorize(access = CommonConstant.Account.RESTAURATEUR)
    public RespVO<Void> modify() {
        return new RespVO<>();
    }

    @DeleteMapping("/delete")
    @Authorize(access = CommonConstant.Account.RESTAURATEUR)
    public RespVO<Void> delete() {
        return new RespVO<>();
    }
}