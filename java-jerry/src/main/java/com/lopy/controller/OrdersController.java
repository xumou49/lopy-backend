package com.lopy.controller;
import com.lopy.common.vo.RespVO;
import com.lopy.service.biz.intf.OrdersService;
import com.lopy.entity.OrdersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @RequestMapping("/list")
    public RespVO list(@RequestParam Map<String, Object> params){

        return RespVO.ok();
    }


    @RequestMapping("/info/{id}")
    public RespVO info(@PathVariable("id") Long id){
		OrdersEntity orders = ordersService.getById(id);

        return RespVO.ok().put("orders", orders);
    }

    @RequestMapping("/save")
    public RespVO save(@RequestBody OrdersEntity orders){
		ordersService.save(orders);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO update(@RequestBody OrdersEntity orders){
		ordersService.updateById(orders);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO delete(@RequestBody Long[] ids){
		ordersService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
