package com.lopy.common.form.card;

import lombok.Data;

@Data
public class UserCardForm {

    private Long userId;
    private String token;

    private String stripeId;
    private String brand;
    private String funding;
    private String lastFour;
    private Long expMonth;
    private Long expYear;
    private String country;
    private String cvcCheck;
    private String fingerprint;
}
