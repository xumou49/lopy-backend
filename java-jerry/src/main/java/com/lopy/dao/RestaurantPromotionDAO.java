package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lopy.entity.Restaurant;
import com.lopy.entity.RestaurantPromotion;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RestaurantPromotionDAO extends BaseMapper<RestaurantPromotion> {
	
}
