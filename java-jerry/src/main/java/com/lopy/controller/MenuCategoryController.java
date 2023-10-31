package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.menu.MenuCategoryDTO;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.MenuCategory;
import com.lopy.service.biz.intf.MenuCategoryService;
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

@Tag(name = "Menu Category API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/menu-category")
public class MenuCategoryController {

    @Autowired
    private MenuCategoryService menuCategoryService;

    @PostMapping("/list")
    public RespVO<Void> list() {
        return RespVO.ok();
    }

    @GetMapping("/info")
    public RespVO<MenuCategory> info(@RequestParam Long id) {
		MenuCategory menuCategory = menuCategoryService.getById(id);
        return RespVO.ok(menuCategory);
    }

    @PutMapping("/save")
    public RespVO<Void> save(@RequestBody MenuCategoryDTO menuCategoryDTO) {
        return RespVO.ok();
    }

    @PutMapping("/update")
    public RespVO<Void> update(@RequestBody MenuCategoryDTO menuCategoryDTO) {
        return RespVO.ok();
    }

    @PutMapping("/delete")
    public RespVO<Void> delete(@RequestBody List<Long> ids){
		menuCategoryService.removeByIds(ids);
        return RespVO.ok();
    }
}
