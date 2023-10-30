package com.lopy.controller;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.MenuItemEntity;
import com.lopy.service.biz.intf.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("/menuitem")
public class MenuItemController {
    @Autowired
    private MenuItemService menuItemService;

    @RequestMapping("/list")
    public RespVO list(@RequestParam Map<String, Object> params){

        return RespVO.ok();
    }

    @RequestMapping("/info/{id}")
    public RespVO info(@PathVariable("id") Long id){
		MenuItemEntity menuItem = menuItemService.getById(id);

        return RespVO.ok().put("menuItem", menuItem);
    }

    @RequestMapping("/save")
    public RespVO save(@RequestBody MenuItemEntity menuItem){
		menuItemService.save(menuItem);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO update(@RequestBody MenuItemEntity menuItem){
		menuItemService.updateById(menuItem);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO delete(@RequestBody Long[] ids){
		menuItemService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
