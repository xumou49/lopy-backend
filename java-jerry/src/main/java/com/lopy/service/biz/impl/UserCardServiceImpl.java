package com.lopy.service.biz.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lopy.common.auth.AuthContext;
import com.lopy.common.dto.card.UserCardListDTO;
import com.lopy.common.exception.StripeException;
import com.lopy.common.form.card.UserCardForm;
import com.lopy.common.form.stripe.PaymentMethodForm;
import com.lopy.common.query.UserCardQuery;
import com.lopy.common.utils.MessageUtil;
import com.lopy.common.vo.card.UserCardVO;
import com.lopy.dao.UserCardDAO;
import com.lopy.entity.Customer;
import com.lopy.entity.UserCard;
import com.lopy.service.biz.intf.UserCardService;
import com.lopy.service.stripe.StripeService;
import com.lopy.service.validation.CustomerValidation;
import com.lopy.service.validation.UserCardValidation;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentMethodCreateParams;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service("userCardService")
public class UserCardServiceImpl extends ServiceImpl<UserCardDAO, UserCard> implements UserCardService {

    @Autowired
    private CustomerValidation customerValidation;

    @Autowired
    private UserCardValidation userCardValidation;

    @Autowired
    private StripeService stripeService;

    private UserCardVO toUserCardVO(UserCard userCard) {
        UserCardVO userCardVO = new UserCardVO();
        BeanUtils.copyProperties(userCard, userCardVO);
        return userCardVO;
    }

    @Override
    public List<UserCardVO> listByQuery(UserCardListDTO userCardListDTO) {
        UserCardQuery userCardQuery = UserCardQuery.build(userCardListDTO);
        return baseMapper.selectByQuery(userCardQuery).stream().map(this::toUserCardVO).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserCardForm userCardForm) {
        // get customer
        Customer customer = customerValidation.customerExistChecker(userCardForm.getUserId());

        // create stripe payment method
        PaymentMethodForm paymentMethodForm = new PaymentMethodForm();
        paymentMethodForm.setType(PaymentMethodCreateParams.Type.CARD);
        paymentMethodForm.setToken(userCardForm.getToken());
        paymentMethodForm.setCustomerStripeId(customer.getStripeId());
        PaymentMethod paymentMethod = stripeService.createPaymentMethod(paymentMethodForm);
        PaymentMethod.Card card = paymentMethod.getCard();
        if (card == null) {
            throw new StripeException(MessageUtil.getMessage("error.payment.method.create.fail"));
        }

        // persist to db
        UserCard userCard = new UserCard();
        userCard.setStripeId(paymentMethod.getId());
        userCard.setBrand(card.getBrand());
        userCard.setUserId(userCardForm.getUserId());
        userCard.setFunding(card.getFunding());
        userCard.setLastFour(card.getLast4());
        userCard.setExpMonth(card.getExpMonth());
        userCard.setExpYear(card.getExpYear());
        userCard.setCountry(card.getCountry());
        userCard.setCvcCheck(card.getChecks().getCvcCheck());
        userCard.setFingerPrint(card.getFingerprint());
        userCard.setUserId(userCardForm.getUserId());
        baseMapper.insert(userCard);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        // get payment method
        UserCard userCard = userCardValidation.userCardOperateChecker(id, AuthContext.getUserId());
        // delete stripe payment method
        stripeService.deletePaymentMethod(userCard.getStripeId());
        // remove from db
        baseMapper.deleteById(userCard.getId());
    }
}