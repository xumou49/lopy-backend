package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lopy.common.query.OrderQuery;
import com.lopy.entity.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDAO extends BaseMapper<Order> {

    @Select("select * from c_order where payment_intent_id = #{paymentIntentId} and status = #{status}")
    List<Order> selectByPaymentIntentIdAndStatus(@Param("paymentIntentId") Long paymentIntentId, @Param("status") Integer status);

    @Delete("delete from c_order where payment_intent_id = #{paymentIntentId} and status = #{status}")
    void deleteByPaymentIntentIdAndStatus(@Param("paymentIntentId") Long paymentIntentId, @Param("status") Integer status);

    List<Order> selectByQuery(@Param("query")OrderQuery orderQuery);

    IPage<Order> selectByPageAndQuery(@Param("page") Page<Order> page, @Param("query")OrderQuery orderQuery);
}
