package com.lopy.controller;

import com.lopy.common.auth.Authorize;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.vo.RespVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Tag(name = "Demo API")
@RequestMapping(CommonConstant.API.V1_PATH + "/demo")
@RestController
public class DemoController {

    @Value("${app.version}")
    private String appVersion;

    @PostMapping("/list")
    @Authorize(access = {CommonConstant.Account.RESTAURATEUR, CommonConstant.Account.CUSTOMER})
    public RespVO<Void> list() {
        System.out.println("in role list");
        return RespVO.ok();
    }

    @PostMapping("/page")
    @Authorize(access = {CommonConstant.Account.RESTAURATEUR, CommonConstant.Account.CUSTOMER})
    public RespVO<Void> page() {
        System.out.println("in role page");
        return RespVO.ok();
    }

    @PutMapping("/create")
    // @Authorize(access = CommonConstant.Account.RESTAURATEUR)
    public RespVO<Void> create(@RequestBody String body, @RequestParam String id) {
        System.out.println("in role create");
        return RespVO.ok();
    }

    @PutMapping("/modify")
    @Authorize(access = CommonConstant.Account.RESTAURATEUR)
    public RespVO<Void> modify() {
        return RespVO.ok();
    }

    @DeleteMapping("/delete")
    @Authorize(access = CommonConstant.Account.RESTAURATEUR)
    public RespVO<Void> delete() {
        return RespVO.ok();
    }

    @GetMapping("/test-build")
    public RespVO<?> testBuild() {
        Map<String, Object> params = new HashMap<>();
        params.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        params.put("version", appVersion);
        return RespVO.ok(params);
    }
}
