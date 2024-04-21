package com.lopy.controller;

import com.lopy.common.auth.AuthContext;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.order.OrderDTO;
import com.lopy.common.dto.order.OrderListDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.vo.RespVO;
import com.lopy.common.vo.order.OrderVO;
import com.lopy.service.biz.intf.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Order API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/order")
public class OrderController {
    @Autowired
    private OrderService ordersService;

    /**
     * Returns page of current order & history order of current user.
     * @param orderListDTO: query params
     * @return Order List in Pagination
     */
    @PostMapping("/page")
    public RespVO<PageResult<OrderVO>> page(@RequestBody OrderListDTO orderListDTO) {
        orderListDTO.setUserId(AuthContext.getUserId());
        PageResult<OrderVO> page = ordersService.pageByQuery(orderListDTO);
        return RespVO.ok(page);
    }

    /**
     * Returns list of current order & history order of current user.
     * @param orderListDTO: query params
     * @return Order List
     */
    @PostMapping("/list")
    public RespVO<List<OrderVO>> list(@RequestBody OrderListDTO orderListDTO) {
        orderListDTO.setUserId(AuthContext.getUserId());
        List<OrderVO> list = ordersService.listByQuery(orderListDTO);
        return RespVO.ok(list);
    }

    /**
     * Returns list of current order & history order of current user.
     * @param orderDTO: query params
     * @return Order List
     */
    @PutMapping("/save")
    public RespVO<Void> save(@RequestBody OrderDTO orderDTO) {
        orderDTO.setUserId(AuthContext.getUserId());
        ordersService.createOrder(orderDTO);
        return RespVO.ok();
    }

    @PutMapping("/update")
    public RespVO<Void> update(@RequestBody OrderListDTO orderListDTO) {
        return RespVO.ok();
    }

    @PutMapping("/delete")
    public RespVO<Void> delete(@RequestBody List<Long> ids){
        return RespVO.ok();
    }
}
