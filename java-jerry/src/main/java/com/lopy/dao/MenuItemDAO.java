package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.lopy.entity.MenuItem;
@Mapper
public interface MenuItemDAO extends BaseMapper<MenuItem> {
	
}
