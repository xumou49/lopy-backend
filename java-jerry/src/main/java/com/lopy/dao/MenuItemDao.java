package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.lopy.entity.MenuItem;
@Mapper
public interface MenuItemDao extends BaseMapper<MenuItem> {
	
}
