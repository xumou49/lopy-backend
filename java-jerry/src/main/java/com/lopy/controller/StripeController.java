package com.lopy.controller;

import com.lopy.common.vo.RespVO;
import com.lopy.service.biz.intf.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Stripe API")
@RequestMapping("/stripe")
@RestController
public class StripeController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/sync/customer")
    public RespVO<Void> syncCustomerData() {
        customerService.syncStripeData();
        return RespVO.ok();
    }
}