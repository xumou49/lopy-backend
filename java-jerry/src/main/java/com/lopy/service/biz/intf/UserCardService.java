package com.lopy.service.biz.intf;

import com.lopy.common.dto.card.UserCardDTO;
import com.lopy.common.form.card.UserCardForm;
import com.lopy.common.vo.card.UserCardVO;

import java.util.List;

public interface UserCardService {

    List<UserCardVO> listByQuery(UserCardDTO userCardDTO);

    void save(UserCardForm userCardForm);

    void delete(Long id);
}

