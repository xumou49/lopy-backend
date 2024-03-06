package com.lopy.common.form.card;

import lombok.Data;

@Data
public class UserCardForm {

    private String cardNumber;
    private Integer expYear;
    private Integer expMonth;
    private Long userId;
}
