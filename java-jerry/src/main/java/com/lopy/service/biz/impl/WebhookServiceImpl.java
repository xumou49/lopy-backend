package com.lopy.service.biz.impl;

import com.lopy.service.biz.intf.WebhookService;
import com.lopy.service.stripe.StripeService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("webhookService")
public class WebhookServiceImpl implements WebhookService {

    @Autowired
    private StripeService stripeService;

    @Override
    public void handleStripeEvent(String payload, String signature) {
        Event event = parseToStripeEvent(payload, signature);
        if (event == null) {
            log.info("stripe event is null, no further action is taken");
            return;
        }
        stripeService.handleEvent(event);
    }

    private Event parseToStripeEvent(String payload, String signature) {
        try {
            return Webhook.constructEvent(payload, signature, stripeService.getWebhookSecret());
        } catch (SignatureVerificationException e) {
            log.error("WebhookServiceImpl invokes parseToStripeEvent fails, error:", e);
            return null;
        }
    }
}