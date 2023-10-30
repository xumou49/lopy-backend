package com.lopy.controller;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.MenuCategoryEntity;
import com.lopy.service.biz.intf.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("/menucategory")
public class MenuCategoryController {
    @Autowired
    private MenuCategoryService menuCategoryService;

    @RequestMapping("/list")
    public RespVO list(@RequestParam Map<String, Object> params){

        return RespVO.ok();
    }


    @RequestMapping("/info/{id}")
    public RespVO info(@PathVariable("id") Long id){
		MenuCategoryEntity menuCategory = menuCategoryService.getById(id);

        return RespVO.ok().put("menuCategory", menuCategory);
    }

    @RequestMapping("/save")
    public RespVO save(@RequestBody MenuCategoryEntity menuCategory){
		menuCategoryService.save(menuCategory);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO update(@RequestBody MenuCategoryEntity menuCategory){
		menuCategoryService.updateById(menuCategory);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO delete(@RequestBody Long[] ids){
		menuCategoryService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
