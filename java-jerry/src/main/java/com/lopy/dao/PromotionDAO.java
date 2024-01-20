package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lopy.entity.Promotion;
import com.lopy.entity.Restaurant;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PromotionDAO extends BaseMapper<Promotion> {
	
}
