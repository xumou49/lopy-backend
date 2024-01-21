package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.menu.MenuDTO;
import com.lopy.common.vo.RespVO;
import com.lopy.service.biz.intf.MenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Menu API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/list")
    public RespVO<Void> list() {
        return RespVO.ok();
    }

    @PutMapping("/save")
    public RespVO<Void> save(@RequestBody MenuDTO menuDTO) {
        return RespVO.ok();
    }

    @PutMapping("/update")
    public RespVO<Void> update(@RequestBody MenuDTO menuDTO) {
        return RespVO.ok();
    }

    @PutMapping("/delete")
    public RespVO<Void> delete(@RequestBody List<Long> ids) {
        // menuService.removeByIds(ids);
        return RespVO.ok();
    }
}
