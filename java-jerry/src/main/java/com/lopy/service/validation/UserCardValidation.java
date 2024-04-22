package com.lopy.service.validation;

import com.lopy.common.exception.ValidationException;
import com.lopy.common.utils.MessageUtil;
import com.lopy.dao.UserCardDAO;
import com.lopy.entity.UserCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserCardValidation {

    @Autowired
    private UserCardDAO userCardDAO;

    public UserCard userCardOperateChecker(Long userCardId, Long userId) {
        UserCard userCard = userCardDAO.selectById(userCardId);
        if (userCard == null) {
            throw new ValidationException(MessageUtil.getMessage("error.user-card.not.found"));
        }
        if (!Objects.equals(userCard.getUserId(), userId)) {
            throw new ValidationException(MessageUtil.getMessage("error.user-card.operate-deny"));
        }
        return userCard;
    }
}
