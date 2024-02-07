package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lopy.common.query.OrderQuery;
import com.lopy.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrdersDAO extends BaseMapper<Order> {


    List<Order> selectByQuery(@Param("query")OrderQuery orderQuery);

    IPage<Order> selectByPageAndQuery(@Param("page") Page<Order> page, @Param("query")OrderQuery orderQuery);
}
