package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.vo.RespVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/user")
public class UserController {

    @PostMapping("/list")
    public RespVO<Void> list() {
        return RespVO.ok();
    }

    @PostMapping("/page")
    public RespVO<Void> page() {
        return RespVO.ok();
    }

    @PutMapping("/create")
    public RespVO<Void> create() {
        return RespVO.ok();
    }

    @PutMapping("/modify")
    public RespVO<Void> modify() {
        return RespVO.ok();
    }

    @PutMapping("/delete")
    public RespVO<Void> delete() {
        return RespVO.ok();
    }
}
