package com.lopy.controller;

import com.lopy.common.vo.RespVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User API")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @PostMapping("/list")
    public RespVO list() {
        return RespVO.ok();
    }

    @PostMapping("/page")
    public RespVO page() {
        return RespVO.ok();
    }

    @PutMapping("/create")
    public RespVO create() {
        return RespVO.ok();
    }

    @PutMapping("/modify")
    public RespVO modify() {
        return RespVO.ok();
    }

    @DeleteMapping("/delete")
    public RespVO delete() {
        return RespVO.ok();
    }
}
