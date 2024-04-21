package com.lopy.service.biz.intf;

import com.lopy.common.dto.card.UserCardListDTO;
import com.lopy.common.form.card.UserCardForm;
import com.lopy.common.vo.card.UserCardVO;

import java.util.List;

public interface UserCardService {

    List<UserCardVO> listByQuery(UserCardListDTO userCardListDTO);

    void save(UserCardForm userCardForm);

    void delete(Long id);
}

