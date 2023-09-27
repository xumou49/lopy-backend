package com.lopy.controller;

import com.lopy.common.vo.RespVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Permission API")
@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {

    @PostMapping("/list")
    public RespVO<Void> list() {
        return new RespVO<>();
    }

    @PostMapping("/page")
    public RespVO<Void> page() {
        return new RespVO<>();
    }

    @PutMapping("/create")
    public RespVO<Void> create() {
        return new RespVO<>();
    }

    @PutMapping("/modify")
    public RespVO<Void> modify() {
        return new RespVO<>();
    }

    @DeleteMapping("/delete")
    public RespVO<Void> delete() {
        return new RespVO<>();
    }
}
