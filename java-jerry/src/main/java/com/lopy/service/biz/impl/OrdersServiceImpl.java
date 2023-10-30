package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.dao.OrdersDao;
import com.lopy.entity.OrdersEntity;
import com.lopy.service.biz.intf.OrdersService;
import org.springframework.stereotype.Service;

@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrdersDao, OrdersEntity> implements OrdersService {

}