package com.lopy.service.biz.intf;

import com.lopy.common.dto.order.OrderItemDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.vo.order.OrderItemVO;

import java.util.List;

public interface OrderItemService {

    List<OrderItemVO> listByQuery(OrderItemDTO orderItemDTO);

    PageResult<OrderItemVO> pageByQuery(OrderItemDTO orderItemDTO);
}

