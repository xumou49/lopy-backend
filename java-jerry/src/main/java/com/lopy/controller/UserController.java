package com.lopy.controller;

import com.lopy.common.dto.RespDTO;
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
    public RespDTO<Void> list() {
        return new RespDTO<>();
    }

    @PostMapping("/page")
    public RespDTO<Void> page() {
        return new RespDTO<>();
    }

    @PutMapping("/create")
    public RespDTO<Void> create() {
        return new RespDTO<>();
    }

    @PutMapping("/modify")
    public RespDTO<Void> modify() {
        return new RespDTO<>();
    }

    @DeleteMapping("/delete")
    public RespDTO<Void> delete() {
        return new RespDTO<>();
    }
}
