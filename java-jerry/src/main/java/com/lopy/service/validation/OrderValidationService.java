package com.lopy.service.validation;

import com.lopy.common.exception.ValidationException;
import com.lopy.common.utils.MessageUtil;
import com.lopy.dao.OrderDAO;
import com.lopy.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrderValidationService {

    @Autowired
    private OrderDAO orderDAO;

    public void orderOperateChecker(Long orderId, Long userId) {
        Order order = orderDAO.selectById(orderId);
        if (order == null) {
            throw new ValidationException(MessageUtil.getMessage("error.order.not-exist"));
        }
        if (!Objects.equals(order.getUserId(), userId)) {
            throw new ValidationException(MessageUtil.getMessage("error.order.operate-deny"));
        }
    }
}
