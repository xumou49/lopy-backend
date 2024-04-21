package com.lopy.service.biz.intf;

import com.lopy.common.dto.restaurant.RestaurantListDTO;
import com.lopy.common.pagination.PageResult;
import com.lopy.common.vo.restaurant.RestaurantVO;

import java.util.List;

public interface RestaurantService {

    List<RestaurantVO> listByQuery(RestaurantListDTO restaurantListDTO);

    PageResult<RestaurantVO> pageByQuery(RestaurantListDTO restaurantListDTO);

    RestaurantVO getById(Long id);

}

