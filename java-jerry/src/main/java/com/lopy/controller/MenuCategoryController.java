package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.menu.MenuCategoryDTO;
import com.lopy.common.vo.RespVO;
import com.lopy.service.biz.intf.MenuCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
		// menuCategoryService.removeByIds(ids);
        return RespVO.ok();
    }
}
