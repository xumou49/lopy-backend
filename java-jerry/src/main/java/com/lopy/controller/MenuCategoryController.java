package com.lopy.controller;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.MenuCategory;
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
    public RespVO<Void> list(@RequestParam Map<String, Object> params){

        return RespVO.ok();
    }


    @RequestMapping("/info/{id}")
    public RespVO<Void> info(@PathVariable("id") Long id){
		MenuCategory menuCategory = menuCategoryService.getById(id);

        return RespVO.ok().put("menuCategory", menuCategory);
    }

    @RequestMapping("/save")
    public RespVO<Void> save(@RequestBody MenuCategory menuCategory){
		menuCategoryService.save(menuCategory);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO<Void> update(@RequestBody MenuCategory menuCategory){
		menuCategoryService.updateById(menuCategory);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO<Void> delete(@RequestBody Long[] ids){
		menuCategoryService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
