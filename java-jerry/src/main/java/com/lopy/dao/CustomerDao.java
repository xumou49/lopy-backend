package com.lopy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.lopy.entity.Customer;
@Mapper
public interface CustomerDao extends BaseMapper<Customer> {
	
}
