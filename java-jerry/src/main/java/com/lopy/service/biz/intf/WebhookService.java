package com.lopy.service.biz.intf;

public interface WebhookService {

    void handleStripeEvent(String payload, String signature);
}

