package com.lopy.common.vo.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentVO {

    private Long id;
    private String clientSecret;
}
