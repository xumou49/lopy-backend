package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.dao.OrderItemDao;
import com.lopy.entity.OrderItem;
import com.lopy.service.biz.intf.OrderItemService;
import org.springframework.stereotype.Service;



@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItem> implements OrderItemService {

}