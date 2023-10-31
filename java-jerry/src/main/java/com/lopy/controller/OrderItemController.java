package com.lopy.controller;
import com.lopy.common.vo.RespVO;

import com.lopy.entity.OrderItem;
import com.lopy.service.biz.intf.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;




@RestController
@RequestMapping("/orderitem")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping("/list")
    public RespVO<Void> list(@RequestParam Map<String, Object> params){

        return RespVO.ok();
    }


    @RequestMapping("/info/{id}")
    public RespVO<Void> info(@PathVariable("id") Long id){
		OrderItem orderItem = orderItemService.getById(id);

        return RespVO.ok().put("orderItem", orderItem);
    }

    @RequestMapping("/save")
    public RespVO<Void> save(@RequestBody OrderItem orderItem){
		orderItemService.save(orderItem);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO<Void> update(@RequestBody OrderItem orderItem){
		orderItemService.updateById(orderItem);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO<Void> delete(@RequestBody Long[] ids){
		orderItemService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
