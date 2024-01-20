package com.lopy.service.biz.intf;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lopy.common.dto.restaurant.RestaurantDTO;
import com.lopy.common.vo.RestaurantVO;
import com.lopy.entity.Restaurant;

import java.util.List;

public interface RestaurantService extends IService<Restaurant> {

    List<RestaurantVO> listByQuery(RestaurantDTO restaurantDTO);
}

