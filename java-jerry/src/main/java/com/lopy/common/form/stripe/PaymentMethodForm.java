package com.lopy.common.form.stripe;

import com.stripe.param.PaymentMethodCreateParams;
import lombok.Data;

@Data
public class PaymentMethodForm {

    private String customerStripeId;
    private String token;
    private PaymentMethodCreateParams.Type type;
}
