package com.lopy.service.biz.intf;

import com.lopy.common.dto.order.OrderDTO;
import com.lopy.common.dto.order.OrderListDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.vo.order.OrderPaymentVO;
import com.lopy.common.vo.order.OrderVO;

import java.util.List;

public interface OrderService {
    List<OrderVO> listByQuery(OrderListDTO orderListDTO);

    PageResult<OrderVO> pageByQuery(OrderListDTO orderListDTO);

    OrderPaymentVO createOrder(OrderDTO orderDTO);
}

