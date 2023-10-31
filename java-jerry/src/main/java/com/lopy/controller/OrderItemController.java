package com.lopy.controller;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.order.OrderItemDTO;
import com.lopy.common.vo.RespVO;
import com.lopy.entity.OrderItem;
import com.lopy.service.biz.intf.OrderItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Order Item API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/order-item")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @PostMapping("/list")
    public RespVO<Void> list(){
        return RespVO.ok();
    }

    @GetMapping("/info")
    public RespVO<OrderItem> info(@RequestParam("id") Long id){
		OrderItem orderItem = orderItemService.getById(id);
        return RespVO.ok(orderItem);
    }

    @PutMapping("/save")
    public RespVO<Void> save(@RequestBody OrderItemDTO orderItemDTO){
        return RespVO.ok();
    }

    @PutMapping("/update")
    public RespVO<Void> update(@RequestBody OrderItemDTO orderItemDTO){
        return RespVO.ok();
    }

    @PutMapping("/delete")
    public RespVO<Void> delete(@RequestBody List<Long> ids){
        return RespVO.ok();
    }
}
