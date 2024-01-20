package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.dao.OrdersDAO;
import com.lopy.entity.Order;
import com.lopy.service.biz.intf.OrderService;
import org.springframework.stereotype.Service;

@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrdersDAO, Order> implements OrderService {

}