package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.dao.CustomerDao;
import com.lopy.entity.CustomerEntity;
import com.lopy.service.biz.intf.CustomerService;
import org.springframework.stereotype.Service;


@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDao, CustomerEntity> implements CustomerService {

}