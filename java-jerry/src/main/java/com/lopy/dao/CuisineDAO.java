package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lopy.entity.Cuisine;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CuisineDAO extends BaseMapper<Cuisine> {
	
}
