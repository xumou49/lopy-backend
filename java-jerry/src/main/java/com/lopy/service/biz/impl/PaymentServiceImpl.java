package com.lopy.service.biz.impl;

import com.lopy.common.dto.payment.PaymentDTO;
import com.lopy.common.exception.ServiceException;
import com.lopy.common.form.stripe.PaymentForm;
import com.lopy.common.utils.CollectionUtil;
import com.lopy.common.utils.MessageUtil;
import com.lopy.common.vo.payment.PaymentVO;
import com.lopy.dao.PaymentIntentDAO;
import com.lopy.dao.UserCardDAO;
import com.lopy.entity.Customer;
import com.lopy.entity.PaymentIntent;
import com.lopy.entity.UserCard;
import com.lopy.service.biz.intf.PaymentService;
import com.lopy.service.stripe.StripeService;
import com.lopy.service.validation.CustomerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private CustomerValidation customerValidation;

    @Autowired
    private StripeService stripeService;

    @Autowired
    private UserCardDAO userCardDAO;

    @Autowired
    private PaymentIntentDAO paymentIntentDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PaymentVO create(PaymentDTO paymentDTO) {
        // get customer
        Customer customer = customerValidation.customerExistChecker(paymentDTO.getUserId());

        // get primary method
        List<UserCard> userCards = userCardDAO.selectByUserIdAndPrimaryFlag(1, paymentDTO.getUserId());
        if (CollectionUtil.isEmpty(userCards)) {
            throw new ServiceException(MessageUtil.getMessage("error.payment-method.not-found"));
        }

        // request to stripe
        UserCard userCard = userCards.get(0);
        PaymentForm paymentForm = new PaymentForm();
        paymentForm.setAmount((long) (paymentDTO.getTotalCost() * 100));
        paymentForm.setCustomerId(customer.getStripeId());
        paymentForm.setPaymentMethodId(userCard.getStripeId());
        com.stripe.model.PaymentIntent stripePaymentIntent = stripeService.createPaymentIntent(paymentForm);

        // save payment intent info
        PaymentIntent paymentIntent = new PaymentIntent();
        paymentIntent.setAmount(stripePaymentIntent.getAmount());
        paymentIntent.setStripeId(stripePaymentIntent.getId());
        paymentIntent.setStatus(stripePaymentIntent.getStatus());
        paymentIntent.setNextAction(convertStripeNextActionData(stripePaymentIntent));
        paymentIntent.setUserId(paymentDTO.getUserId());
        paymentIntentDAO.insert(paymentIntent);

        return new PaymentVO(paymentIntent.getId(), stripePaymentIntent.getClientSecret());
    }

    private String convertStripeNextActionData(com.stripe.model.PaymentIntent paymentIntent) {
        if (paymentIntent.getNextAction() == null) {
            return "";
        }
        return paymentIntent.getNextAction().getType();
    }
}