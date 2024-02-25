package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lopy.common.query.OrderItemQuery;
import com.lopy.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderItemDAO extends BaseMapper<OrderItem> {

    List<OrderItem> selectByQuery(@Param("query") OrderItemQuery orderItemQuery);

    IPage<OrderItem> selectByPageAndQuery(@Param("page") Page<OrderItem> page, @Param("query") OrderItemQuery orderItemQuery);
}
