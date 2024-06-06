package com.lopy.service.stripe;

import com.lopy.common.form.stripe.CustomerForm;
import com.lopy.common.form.stripe.PaymentForm;
import com.lopy.common.form.stripe.PaymentMethodForm;
import com.lopy.common.vo.config.StripeVO;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;

public interface StripeService {

    StripeVO getStripeConfig();

    PaymentMethod createPaymentMethod(PaymentMethodForm paymentMethodForm);

    PaymentIntent createPaymentIntent(PaymentForm paymentForm);

    void deletePaymentMethod(String id);

    Customer createCustomer(CustomerForm customerForm);

    void handleEvent(Event event);

    String getWebhookSecret();
}
