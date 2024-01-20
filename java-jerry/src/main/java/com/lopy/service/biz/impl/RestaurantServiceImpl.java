package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.common.dto.restaurant.RestaurantDTO;
import com.lopy.common.vo.RestaurantVO;
import com.lopy.dao.RestaurantDAO;
import com.lopy.entity.Restaurant;
import com.lopy.service.biz.intf.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("restaurantService")
public class RestaurantServiceImpl extends ServiceImpl<RestaurantDAO, Restaurant> implements RestaurantService {

    @Override
    public List<RestaurantVO> listByQuery(RestaurantDTO restaurantDTO) {

        return null;
    }
}