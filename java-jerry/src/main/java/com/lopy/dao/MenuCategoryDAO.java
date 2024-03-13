package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lopy.common.query.MenuCategoryQuery;
import com.lopy.entity.MenuCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuCategoryDAO extends BaseMapper<MenuCategory> {
    
    List<MenuCategory> selectByQuery(@Param("query") MenuCategoryQuery menuCategoryQuery);
    
    IPage<MenuCategory> selectByPageAndQuery(@Param("page") Page<MenuCategory> page, @Param("query") MenuCategoryQuery menuCategoryQuery);
}
