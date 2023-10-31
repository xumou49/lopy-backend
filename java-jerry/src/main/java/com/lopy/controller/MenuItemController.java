package com.lopy.controller;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.MenuItem;
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
    public RespVO<Void> list(@RequestParam Map<String, Object> params){

        return RespVO.ok();
    }

    @RequestMapping("/info/{id}")
    public RespVO<MenuItem> info(@PathVariable("id") Long id){
		MenuItem menuItem = menuItemService.getById(id);
        return RespVO.ok(menuItem);
    }

    @RequestMapping("/save")
    public RespVO<Void> save(@RequestBody MenuItem menuItem){
		menuItemService.save(menuItem);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO<Void> update(@RequestBody MenuItem menuItem){
		menuItemService.updateById(menuItem);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO<Void> delete(@RequestBody Long[] ids){
		menuItemService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
