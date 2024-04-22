package com.lopy.service.stripe;

import com.lopy.common.exception.ServiceException;
import com.lopy.common.form.stripe.CustomerForm;
import com.lopy.common.form.stripe.PaymentMethodForm;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.param.PaymentMethodCreateParams;
import com.stripe.param.PaymentMethodDetachParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.secret_key}")
    private String stripeSecretKey;


    @PostConstruct
    public void initStripe() {
        Stripe.apiKey = stripeSecretKey;
    }

    public Customer createCustomer(CustomerForm customerForm) {
        try {
            CustomerCreateParams params =
                    CustomerCreateParams.builder()
                            .setName(customerForm.getEmail())  // use email for both name & email
                            .setEmail(customerForm.getEmail())
                            .build();
            return Customer.create(params);
        } catch (StripeException e) {
            log.error("createCustomer invokes exception, error:", e);
            throw new ServiceException("fail to create customer");
        }
    }

    public PaymentMethod createPaymentMethod(PaymentMethodForm paymentMethodForm) {
        try {
            // Create payment method params
            PaymentMethodCreateParams.Token t = PaymentMethodCreateParams.Token.builder()
                    .setToken(paymentMethodForm.getToken())
                    .build();
            PaymentMethodCreateParams pmParams = PaymentMethodCreateParams.builder()
                    .setType(paymentMethodForm.getType())
                    .setCard(t)
                    .build();

            // Create payment method
            PaymentMethod paymentMethod = PaymentMethod.create(pmParams);
            PaymentMethodAttachParams pmaParams =
                    PaymentMethodAttachParams.builder()
                            .setCustomer(paymentMethodForm.getCustomerStripeId())
                            .build();
            return paymentMethod.attach(pmaParams);
        } catch (StripeException e) {
            log.error("createCustomer invokes exception, error:", e);
            throw new ServiceException("fail to create customer");
        }
    }

    @Override
    public void deletePaymentMethod(String id) {
        try {
            PaymentMethod resource = PaymentMethod.retrieve(id);
            PaymentMethodDetachParams params = PaymentMethodDetachParams.builder().build();
            resource.detach(params);
        } catch (StripeException e) {
            log.error("deletePaymentMethod invokes exception, error:", e);
            throw new ServiceException("fail detach payment method");
        }
    }
}
