package com.lopy.common.constant;

public class StripeConstant {
    private StripeConstant() {}

    public enum EventCategoryEnum {
        PAYMENT_INTENT("payment_intent"),
        PAYMENT_METHOD("payment_method"),
        CUSTOMER("customer"),
        UNDEFINED("undefined")
        ;

        private final String value;

        EventCategoryEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static final String SIGNATURE_HEADER = "Stripe-Signature";

    public static final class WebhookEvent {
        private WebhookEvent() {}
        public static final String TYPE_PAYMENT_INTENT_CREATED = "payment_intent.created";
        public static final String TYPE_PAYMENT_INTENT_CANCELED = "payment_intent.canceled";
        public static final String TYPE_PAYMENT_INTENT_FAILED = "payment_intent.payment_failed";
        public static final String TYPE_PAYMENT_INTENT_REQUIRED_ACTION = "payment_intent.requires_action";
        public static final String TYPE_PAYMENT_INTENT_SUCCEEDED = "payment_intent.succeeded";
    }

    public static final class PaymentIntent {
        private PaymentIntent () {}
        public static final String STATUS_SUCCESS = "succeeded";
        public static final String STATUS_PROCESSING = "processing";
        public static final String STATUS_CANCELED = "canceled";
        public static final String STATUS_REQUIRES_ACTION = "requires_action";
        public static final String STATUS_REQUIRES_CAPTURE = "requires_capture";
        public static final String STATUS_REQUIRES_CONFIRMATION = "requires_confirmation";
        public static final String STATUS_REQUIRES_PAYMENT_METHOD = "requires_payment_method";
    }
}
