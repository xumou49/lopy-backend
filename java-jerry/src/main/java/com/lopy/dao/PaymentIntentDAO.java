package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lopy.entity.PaymentIntent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PaymentIntentDAO extends BaseMapper<PaymentIntent> {

    @Select("select * from c_payment_intent where stripe_id = #{stripeId}")
    List<PaymentIntent> selectByStripeId(@Param("stripeId") String stripeId);
}
