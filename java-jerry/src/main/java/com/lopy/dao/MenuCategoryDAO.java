package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lopy.common.query.MenuCategoryQuery;
import com.lopy.entity.MenuCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenuCategoryDAO extends BaseMapper<MenuCategory> {

    @Select("select * from c_menu_category where restaurant_id = #{restaurantId} and status = #{status}")
    List<MenuCategory> selectByRestaurantIdAndStatus(@Param("restaurantId") Long restaurantId, @Param("status") Integer status);
    
    List<MenuCategory> selectByQuery(@Param("query") MenuCategoryQuery menuCategoryQuery);
    
    IPage<MenuCategory> selectByPageAndQuery(@Param("page") Page<MenuCategory> page, @Param("query") MenuCategoryQuery menuCategoryQuery);
}
