package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.lopy.entity.Customer;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerDAO extends BaseMapper<Customer> {

    @Select("select * from c_customer where user_id = #{userId}")
    Customer selectByUserId(Long userId);
}
