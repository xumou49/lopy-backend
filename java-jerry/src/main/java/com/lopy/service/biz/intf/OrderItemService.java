package com.lopy.service.biz.intf;

import com.lopy.common.dto.order.OrderItemListDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.vo.order.OrderItemVO;

import java.util.List;

public interface OrderItemService {

    List<OrderItemVO> listByQuery(OrderItemListDTO orderItemListDTO);

    PageResult<OrderItemVO> pageByQuery(OrderItemListDTO orderItemListDTO);
}

