package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.menu.MenuItemDTO;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.MenuItem;
import com.lopy.service.biz.intf.MenuItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Menu Item API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/menu-item")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/list")
    public RespVO<Void> list() {

        return RespVO.ok();
    }

    @GetMapping("/info")
    public RespVO<MenuItem> info(@RequestParam Long id){
		MenuItem menuItem = menuItemService.getById(id);
        return RespVO.ok(menuItem);
    }

    @PutMapping("/save")
    public RespVO<Void> save(@RequestBody MenuItemDTO menuItemDTO){
        return RespVO.ok();
    }

    @PutMapping("/update")
    public RespVO<Void> update(@RequestBody MenuItem menuItemDTO){
        return RespVO.ok();
    }

    @PutMapping("/delete")
    public RespVO<Void> delete(@RequestBody List<Long> ids) {
        return RespVO.ok();
    }
}
