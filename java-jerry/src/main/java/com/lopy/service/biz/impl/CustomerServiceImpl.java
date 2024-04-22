package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.common.form.stripe.CustomerForm;
import com.lopy.dao.CustomerDAO;
import com.lopy.dao.UserDAO;
import com.lopy.entity.Customer;
import com.lopy.entity.User;
import com.lopy.service.biz.intf.CustomerService;
import com.lopy.service.stripe.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("customerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerDAO, Customer> implements CustomerService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private StripeService stripeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(User user) {
        // create stripe customer first
        com.stripe.model.Customer stripeCustomer = stripeService.createCustomer(new CustomerForm(user.getEmail()));
        Customer customer = new Customer();
        customer.setStripeId(stripeCustomer.getId());
        customer.setUserId(user.getId());
        baseMapper.insert(customer);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncStripeData() {
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
        queryWrapper.and(o -> o.isNull("stripe_id").or().eq("stripe_id", ""));
        List<Customer> customers = baseMapper.selectList(queryWrapper);
        for (Customer customer : customers) {
            User user = userDAO.selectById(customer.getId());
            if (user == null) {
                continue;
            }
            com.stripe.model.Customer stripeCustomer = stripeService.createCustomer(new CustomerForm(user.getEmail()));
            if (stripeCustomer == null) {
                continue;
            }
            customer.setStripeId(stripeCustomer.getId());
            baseMapper.updateById(customer);
        }
    }
}