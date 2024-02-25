package com.lopy.controller;

import com.lopy.common.auth.AuthContext;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.order.OrderItemDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.vo.RespVO;
import com.lopy.common.vo.order.OrderItemVO;
import com.lopy.service.biz.intf.OrderItemService;
import com.lopy.service.validation.OrderValidationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Order Item API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/order-item")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderValidationService orderValidationService;

    /**
     * Returns page of associated order item for the given order.
     * @param orderItemDTO: query params
     * @return Order Item List in Pagination
     */
    @PostMapping("/page")
    public RespVO<PageResult<OrderItemVO>> page(@RequestBody OrderItemDTO orderItemDTO) {
        orderValidationService.orderOperateChecker(orderItemDTO.getOrderId(), AuthContext.getUserId());
        PageResult<OrderItemVO> page = orderItemService.pageByQuery(orderItemDTO);
        return RespVO.ok(page);
    }

    /**
     * Returns list of associated order item for the given order.
     * @param orderItemDTO: query params
     * @return Order Item List
     */
    @PostMapping("/list")
    public RespVO<List<OrderItemVO>> list(OrderItemDTO orderItemDTO){
        orderValidationService.orderOperateChecker(orderItemDTO.getOrderId(), AuthContext.getUserId());
        List<OrderItemVO> list = orderItemService.listByQuery(orderItemDTO);
        return RespVO.ok(list);
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
