package com.lopy.service.biz.intf;

import com.lopy.common.dto.restaurant.RestaurantDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.vo.restaurant.RestaurantVO;

import java.util.List;

public interface RestaurantService {

    List<RestaurantVO> listByQuery(RestaurantDTO restaurantDTO);

    PageResult<RestaurantVO> pageByQuery(RestaurantDTO restaurantDTO);

    RestaurantVO getById(Long id);

}

