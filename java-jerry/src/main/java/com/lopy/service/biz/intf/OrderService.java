package com.lopy.service.biz.intf;

import com.lopy.common.dto.order.OrderDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.vo.order.OrderVO;

import java.util.List;

public interface OrderService {
    List<OrderVO> listByQuery(OrderDTO orderDTO);

    PageResult<OrderVO> pageByQuery(OrderDTO orderDTO);
}

