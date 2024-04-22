package com.lopy.service.biz.intf;

import com.lopy.entity.User;

public interface CustomerService {

    void create(User user);

    void syncStripeData();
}


