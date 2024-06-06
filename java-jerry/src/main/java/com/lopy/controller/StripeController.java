package com.lopy.controller;

import com.lopy.common.vo.RespVO;
import com.lopy.common.vo.config.StripeVO;
import com.lopy.service.biz.intf.CustomerService;
import com.lopy.service.stripe.StripeService;
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
    private StripeService stripeService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/config")
    public RespVO<StripeVO> config() {
        StripeVO vo = stripeService.getStripeConfig();
        return RespVO.ok(vo);
    }

    @GetMapping("/sync/customer")
    public RespVO<Void> syncCustomerData() {
        customerService.syncStripeData();
        return RespVO.ok();
    }
}