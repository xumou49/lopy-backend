package com.lopy.service.stripe;

import com.lopy.common.constant.CommonConstant;
import com.lopy.common.constant.StripeConstant;
import com.lopy.common.exception.ServiceException;
import com.lopy.common.form.stripe.CustomerForm;
import com.lopy.common.form.stripe.PaymentForm;
import com.lopy.common.form.stripe.PaymentMethodForm;
import com.lopy.common.utils.CollectionUtil;
import com.lopy.common.utils.JsonUtil;
import com.lopy.common.vo.config.StripeVO;
import com.lopy.dao.OrderDAO;
import com.lopy.dao.PaymentIntentDAO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.StripeObject;
import com.stripe.param.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class StripeServiceImpl implements StripeService {

    @Value("${stripe.public_key}")
    private String publicKey;

    @Value("${stripe.secret_key}")
    private String secretKey;

    @Value("${stripe.webhook_secret}")
    private String webhookSecret;

    @Autowired
    private PaymentIntentDAO paymentIntentDAO;

    @Autowired
    private OrderDAO orderDAO;

    @PostConstruct
    public void initStripe() {
        Stripe.apiKey = secretKey;
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

    @Override
    public StripeVO getStripeConfig() {
        return new StripeVO(publicKey);
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
                            .setCustomer(paymentMethodForm.getCustomerId())
                            .build();
            return paymentMethod.attach(pmaParams);
        } catch (StripeException e) {
            log.error("createCustomer invokes exception, error:", e);
            throw new ServiceException("fail to create customer");
        }
    }

    @Override
    public PaymentIntent createPaymentIntent(PaymentForm paymentForm) {
        PaymentIntentCreateParams.Builder paramsBuilder = new PaymentIntentCreateParams
                .Builder()
                .setCustomer(paymentForm.getCustomerId())
                .setPaymentMethod(paymentForm.getPaymentMethodId())
                .setCurrency(paymentForm.getCurrency())
                .setAmount(paymentForm.getAmount());

        PaymentIntentCreateParams createParams = paramsBuilder.build();

        try {
            PaymentIntentConfirmParams params =
                    PaymentIntentConfirmParams.builder()
                            .setPaymentMethod(paymentForm.getPaymentMethodId())
                            .build();
            PaymentIntent paymentIntent = PaymentIntent.create(createParams);
            PaymentIntent confirmPaymentIntent = paymentIntent.confirm(params);
            System.out.println("paymentIntent= " + paymentIntent.getId());
            System.out.println("confirmPaymentIntent = " + confirmPaymentIntent.getId());
            return confirmPaymentIntent;
        } catch (Exception e) {
            log.error("createPaymentIntent invokes exception, error:", e);
            throw new ServiceException("fail to create payment intent");
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

    @Override
    public void handleEvent(Event event) {
        log.info("receive stripe webhook event: {}", event.getType());
        StripeConstant.EventCategoryEnum eventCategory = getEventCategory(event.getType());
        switch (eventCategory) {
            case PAYMENT_INTENT:
                handlePaymentIntentEvent(event.getData(), event.getType());
                break;
            case UNDEFINED:
                log.info("receive stripe webhook event without handler found, bypass");
        }
    }

    private void handlePaymentIntentEvent(Event.Data data, String paymentIntentEventType) {
        PaymentIntent paymentIntent = parseStripeObj(data, PaymentIntent.class);
        if (paymentIntent == null) {
            log.info("handlePaymentIntentEvent, fail to parse paymentIntentObj");
            return;
        }
        switch (paymentIntentEventType) {
            case StripeConstant.WebhookEvent.TYPE_PAYMENT_INTENT_SUCCEEDED:
                handlePaymentIntentSucceed(paymentIntent);
                break;
            case StripeConstant.WebhookEvent.TYPE_PAYMENT_INTENT_FAILED:
                handlePaymentIntentFailed(paymentIntent);
                break;
            case StripeConstant.WebhookEvent.TYPE_PAYMENT_INTENT_CREATED:
            case StripeConstant.WebhookEvent.TYPE_PAYMENT_INTENT_REQUIRED_ACTION:
            case StripeConstant.WebhookEvent.TYPE_PAYMENT_INTENT_CANCELED:
                handlePaymentIntentMisc(paymentIntent);
                break;
            default:
                log.info("receive stripe webhook payment-intent event without handler found, bypass");
        }
    }

    public StripeConstant.EventCategoryEnum getEventCategory(String type) {
        String[] split = type.split("\\.");
        if (split.length == 0) {
            return StripeConstant.EventCategoryEnum.UNDEFINED;
        }
        for (StripeConstant.EventCategoryEnum value : StripeConstant.EventCategoryEnum.values()) {
            if (value.getValue().equals(split[0])) {
                return value;
            }
        }
        return StripeConstant.EventCategoryEnum.UNDEFINED;
    }

    private void handlePaymentIntentSucceed(PaymentIntent paymentIntent) {
        com.lopy.entity.PaymentIntent paymentIntentEntity = getPaymentIntentEntityAndUpdateStatus(paymentIntent);
        if (paymentIntentEntity == null) {
            return;
        }

        // update the order status which associates with the payment intent
        List<com.lopy.entity.Order> orders = orderDAO.selectByPaymentIntentIdAndStatus(paymentIntentEntity.getId(), CommonConstant.Order.STATUS_UNPAID);
        orders.forEach(order -> {
            // filter out the unpaid order & change its status to pending
            order.setStatus(CommonConstant.Order.STATUS_PENDING);
            orderDAO.updateById(order);
        });
    }

    private void handlePaymentIntentFailed(PaymentIntent paymentIntent) {
        com.lopy.entity.PaymentIntent paymentIntentEntity = getPaymentIntentEntityAndUpdateStatus(paymentIntent);
        if (paymentIntentEntity == null) {
            return;
        }

        // remove the order which associates with the payment intent
        orderDAO.deleteByPaymentIntentIdAndStatus(paymentIntentEntity.getId(), CommonConstant.Order.STATUS_UNPAID);
    }

    private void handlePaymentIntentMisc(PaymentIntent paymentIntent) {
        // common handler for all payment intent events, simply update the status of db entity
        getPaymentIntentEntityAndUpdateStatus(paymentIntent);
    }

    private com.lopy.entity.PaymentIntent getPaymentIntentEntityAndUpdateStatus(PaymentIntent paymentIntent) {
        // find the payment intent entity by stripe id & update its status
        List<com.lopy.entity.PaymentIntent> paymentIntents = paymentIntentDAO.selectByStripeId(paymentIntent.getId());
        if (CollectionUtil.isEmpty(paymentIntents)) {
            log.error("handlePaymentIntentSucceed, payment intent not found, id: {}", paymentIntent.getId());
            return null;
        }
        com.lopy.entity.PaymentIntent paymentIntentEntity = paymentIntents.get(0);
        paymentIntentEntity.setStatus(paymentIntent.getStatus());
        paymentIntentDAO.updateById(paymentIntentEntity);
        return paymentIntentEntity;
    }

    private <T> T parseStripeObj(Event.Data data, Class<T> clazz) {
        StripeObject object = data.getObject();
        if (object == null) {
            return null;
        }
        return JsonUtil.fromJson(object.toJson(), clazz);
    }

    @Override
    public String getWebhookSecret() {
        return this.webhookSecret;
    }
}
