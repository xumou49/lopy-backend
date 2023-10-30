package com.lopy.controller;

import com.lopy.common.vo.RespVO;
import com.lopy.entity.MenuEntity;
import com.lopy.service.biz.intf.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/list")
    public RespVO list(@RequestParam Map<String, Object> params) {

        return RespVO.ok();
    }


    @RequestMapping("/info/{id}")
    public RespVO info(@PathVariable("id") Long id) {
        MenuEntity menu = menuService.getById(id);

        return RespVO.ok().put("menu", menu);
    }

    @RequestMapping("/save")
    public RespVO save(@RequestBody MenuEntity menu) {
        menuService.save(menu);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO update(@RequestBody MenuEntity menu) {
        menuService.updateById(menu);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO delete(@RequestBody Long[] ids) {
        menuService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
