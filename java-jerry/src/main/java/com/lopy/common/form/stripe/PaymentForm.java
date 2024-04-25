package com.lopy.common.form.stripe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentForm {

    private Long amount;
    private String currency = "sgd";
    private String customerId;
    private String paymentMethodId;
}
