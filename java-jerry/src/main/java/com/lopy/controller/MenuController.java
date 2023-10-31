package com.lopy.controller;

import com.lopy.common.vo.RespVO;
import com.lopy.entity.Menu;
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
    public RespVO<Void> list(@RequestParam Map<String, Object> params) {

        return RespVO.ok();
    }


    @RequestMapping("/info/{id}")
    public RespVO<Void> info(@PathVariable("id") Long id) {
        Menu menu = menuService.getById(id);

        return RespVO.ok().put("menu", menu);
    }

    @RequestMapping("/save")
    public RespVO<Void> save(@RequestBody Menu menu) {
        menuService.save(menu);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO<Void> update(@RequestBody Menu menu) {
        menuService.updateById(menu);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO<Void> delete(@RequestBody Long[] ids) {
        menuService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
