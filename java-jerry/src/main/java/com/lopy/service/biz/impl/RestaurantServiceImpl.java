package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.dao.RestaurantDao;
import com.lopy.entity.Restaurant;
import com.lopy.service.biz.intf.RestaurantService;
import org.springframework.stereotype.Service;

@Service("restaurantService")
public class RestaurantServiceImpl extends ServiceImpl<RestaurantDao, Restaurant> implements RestaurantService {

}