package com.lopy.service.biz.intf;


import com.lopy.common.dto.payment.PaymentDTO;
import com.lopy.common.vo.payment.PaymentVO;

public interface PaymentService {

    PaymentVO create(PaymentDTO paymentDTO);
}

