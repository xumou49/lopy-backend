package com.lopy.service.stripe;

import com.lopy.common.form.stripe.CustomerForm;
import com.lopy.common.form.stripe.PaymentMethodForm;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;

public interface StripeService {

    PaymentMethod createPaymentMethod(PaymentMethodForm paymentMethodForm);

    void deletePaymentMethod(String id);

    Customer createCustomer(CustomerForm customerForm);
}
