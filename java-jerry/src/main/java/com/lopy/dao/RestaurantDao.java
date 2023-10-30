package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.lopy.entity.RestaurantEntity;

@Mapper
public interface RestaurantDao extends BaseMapper<RestaurantEntity> {
	
}
