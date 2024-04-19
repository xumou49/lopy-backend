package com.lopy.service.stripe;

import com.lopy.common.form.card.UserCardForm;
import com.lopy.common.pojo.OAuth;

public interface StripeService {

    UserCardForm addCreditCard(UserCardForm userCardForm);

}
