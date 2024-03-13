package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.common.auth.AuthContext;
import com.lopy.common.dto.card.UserCardDTO;
import com.lopy.common.form.card.UserCardForm;
import com.lopy.common.query.UserCardQuery;
import com.lopy.common.vo.card.UserCardVO;
import com.lopy.dao.UserCardDAO;
import com.lopy.entity.UserCard;
import com.lopy.service.biz.intf.UserCardService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Token;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.param.PaymentMethodCreateParams;
import com.stripe.param.TokenCreateParams;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("userCardService")
public class UserCardServiceImpl extends ServiceImpl<UserCardDAO, UserCard> implements UserCardService {

    private UserCardVO toUserCardVO(UserCard userCard) {
        UserCardVO userCardVO = new UserCardVO();
        BeanUtils.copyProperties(userCard, userCardVO);
        return userCardVO;
    }

    @Override
    public List<UserCardVO> listByQuery(UserCardDTO userCardDTO) {
        UserCardQuery userCardQuery = UserCardQuery.build(userCardDTO);
        return baseMapper.selectByQuery(userCardQuery).stream().map(this::toUserCardVO).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserCardForm userCardForm) {
        Long userId = AuthContext.getUserId();

        Stripe.apiKey = "sk_test_51NS9mDHUWFw9hO0YbLqzUgfUjNaVRMPhOw0N4pJcLWI84EAzQaJMmzzoQgjlpmyIyUxSormkRPVJYQ41060jhDOW00JMnvHBWE";

        try {

            CustomerCreateParams params =
                    CustomerCreateParams.builder()
                            .setName(userCardForm.getToken())
                            .setEmail(userCardForm.getToken() + "@example.com")
                            .build();
            Customer customer = Customer.create(params);

            // Create payment method params
            PaymentMethodCreateParams.Token t = PaymentMethodCreateParams.Token.builder()
                    .setToken(userCardForm.getToken())
                    .build();
            PaymentMethodCreateParams pmParams = PaymentMethodCreateParams.builder()
                    .setType(PaymentMethodCreateParams.Type.CARD)
                    .setCard(t)
                    .build();

            // Create payment method
            PaymentMethod paymentMethod = PaymentMethod.create(pmParams);

            PaymentMethodAttachParams pmaParams =
                    PaymentMethodAttachParams.builder()
                            .setCustomer(customer.getId())
                            .build();
            PaymentMethod pm = paymentMethod.attach(pmaParams);

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

            baseMapper.save(userCardForm);

            log.debug(paymentMethod.toString());
        } catch (StripeException e) {
            throw new RuntimeException(e);
        }

//            Map<String, Object> customerData = new HashMap<>();
//            customerData.put("name", "customer01");
//            customerData.put("email", "customer01@gmail.com");
//            Customer customer = Customer.create(customerData);

        //get error
        //java.lang.RuntimeException: com.stripe.exception.CardException: Sending credit card numbers directly to the Stripe API is generally unsafe.
//            Map<String, Object> tokenData = new HashMap<>();
//            tokenData.put("number", "4242424242424242");
//            tokenData.put("exp_month", 8L);
//            tokenData.put("exp_year", 2026L);
//            tokenData.put("cvc", "123");
//            Map<String, Object> params = new HashMap<>();
//            params.put("card", tokenData);
//            Card card = customer.createCard(params);

//            Card card = customer.createCard(userCardForm.getToken());
//            userCardForm.setBrand(card.getBrand());
//            userCardForm.setCountry(card.getCountry());
//            userCardForm.setCvcCheck(card.getCvcCheck());
//            userCardForm.setExpMonth(card.getExpMonth());
//            userCardForm.setFingerprint(card.getFingerprint());
//            userCardForm.setFunding(card.getFunding());
//            userCardForm.setLastFour(card.getLast4());
//            log.debug(card.toString());


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Long userId = AuthContext.getUserId();
        // implement here
    }
}