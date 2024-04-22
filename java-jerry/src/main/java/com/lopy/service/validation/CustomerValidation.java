package com.lopy.service.validation;

import com.lopy.common.exception.ValidationException;
import com.lopy.common.utils.MessageUtil;
import com.lopy.dao.CustomerDAO;
import com.lopy.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerValidation {

    @Autowired
    private CustomerDAO customerDAO;

    public Customer customerExistChecker(Long userId) {
        Customer customer = customerDAO.selectByUserId(userId);
        if (customer == null) {
            throw new ValidationException(MessageUtil.getMessage("error.customer.not-found"));
        }
        return customer;
    }
}
