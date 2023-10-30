package com.lopy.controller;
import com.lopy.common.vo.RespVO;

import com.lopy.entity.OrderItemEntity;
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
    public RespVO list(@RequestParam Map<String, Object> params){

        return RespVO.ok();
    }


    @RequestMapping("/info/{id}")
    public RespVO info(@PathVariable("id") Long id){
		OrderItemEntity orderItem = orderItemService.getById(id);

        return RespVO.ok().put("orderItem", orderItem);
    }

    @RequestMapping("/save")
    public RespVO save(@RequestBody OrderItemEntity orderItem){
		orderItemService.save(orderItem);

        return RespVO.ok();
    }

    @RequestMapping("/update")
    public RespVO update(@RequestBody OrderItemEntity orderItem){
		orderItemService.updateById(orderItem);

        return RespVO.ok();
    }

    @RequestMapping("/delete")
    public RespVO delete(@RequestBody Long[] ids){
		orderItemService.removeByIds(Arrays.asList(ids));

        return RespVO.ok();
    }

}
