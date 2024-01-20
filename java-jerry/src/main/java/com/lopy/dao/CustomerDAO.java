package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.lopy.entity.Customer;
@Mapper
public interface CustomerDAO extends BaseMapper<Customer> {
	
}
