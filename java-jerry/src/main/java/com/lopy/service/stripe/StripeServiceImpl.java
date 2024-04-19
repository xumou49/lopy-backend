package com.lopy.service.stripe;

import com.lopy.common.form.card.UserCardForm;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.param.PaymentMethodCreateParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.secret_key}")
    private String stripeSecretKey;

    @Override
    public UserCardForm addCreditCard(UserCardForm userCardForm) {
        Stripe.apiKey = stripeSecretKey;

        Customer customer = createCustomer(userCardForm.getToken());
        PaymentMethod pm = createPaymentMethod(userCardForm.getToken(), customer.getId());
        PaymentMethod.Card card = pm.getCard();

        userCardForm.setStripeId(pm.getId());
        userCardForm.setBrand(card.getBrand());
        userCardForm.setFunding(card.getFunding());
        userCardForm.setLastFour(card.getLast4());
        userCardForm.setExpMonth(card.getExpMonth());
        userCardForm.setExpYear(card.getExpYear());
        userCardForm.setCountry(card.getCountry());
        userCardForm.setCvcCheck(card.getChecks().getCvcCheck());
        userCardForm.setFingerprint(card.getFingerprint());
        return userCardForm;
    }

    private Customer createCustomer(String token) {
        try {
            CustomerCreateParams params =
                    CustomerCreateParams.builder()
                            .setName(token)
                            .setEmail(token + "@example.com")
                            .build();
            return Customer.create(params);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    private PaymentMethod createPaymentMethod(String token, String customerId) {
        try {
            // Create payment method params
            PaymentMethodCreateParams.Token t = PaymentMethodCreateParams.Token.builder()
                    .setToken(token)
                    .build();
            PaymentMethodCreateParams pmParams = PaymentMethodCreateParams.builder()
                    .setType(PaymentMethodCreateParams.Type.CARD)
                    .setCard(t)
                    .build();

            // Create payment method
            PaymentMethod paymentMethod = PaymentMethod.create(pmParams);
            PaymentMethodAttachParams pmaParams =
                    PaymentMethodAttachParams.builder()
                            .setCustomer(customerId)
                            .build();
            return paymentMethod.attach(pmaParams);
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
