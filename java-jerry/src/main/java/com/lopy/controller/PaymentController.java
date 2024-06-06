package com.lopy.controller;

import com.lopy.common.auth.AuthContext;
import com.lopy.common.constant.CommonConstant;
import com.lopy.common.dto.payment.PaymentDTO;
import com.lopy.common.vo.RespVO;
import com.lopy.common.vo.payment.PaymentVO;
import com.lopy.service.biz.intf.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Payment API")
@RestController
@RequestMapping(CommonConstant.API.V1_PATH + "/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    /**
     * Make the payment for the order
     * @param paymentDTO: params to create payment
     * @return
     */
    @PostMapping("/create")
    public RespVO<PaymentVO> create(@RequestBody PaymentDTO paymentDTO) {
        paymentDTO.setUserId(AuthContext.getUserId());
        PaymentVO paymentVO = paymentService.create(paymentDTO);
        return RespVO.ok(paymentVO);
    }
}
