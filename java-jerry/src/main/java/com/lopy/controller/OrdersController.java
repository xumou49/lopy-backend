package com.lopy.controller;
import com.lopy.common.vo.RespVO;
import com.lopy.service.biz.intf.OrdersService;
import com.lopy.entity.Order;
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
    public RespVO<Void> list(@RequestParam Map<String, Object> params){

        return RespVO.ok();
    }


    @RequestMapping("/info/{id}")
    public RespVO<Order> info(@PathVariable("id") Long id){
		Order order = ordersService.getById(id);
        return RespVO.ok(order);
    }

    @RequestMapping("/save")
    public RespVO<Void> save(@RequestBody Order orders){
		ordersService.save(orders);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO<Void> update(@RequestBody Order orders){
		ordersService.updateById(orders);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO<Void> delete(@RequestBody Long[] ids){
		ordersService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
