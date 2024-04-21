package com.lopy.common.query;

import com.lopy.common.dto.card.UserCardListDTO;
import lombok.Data;

@Data
public class UserCardQuery {

    private String brand;
    private Long userId;

    public static UserCardQuery build(UserCardListDTO userCardListDTO) {
        UserCardQuery userCardQuery = new UserCardQuery();
        userCardQuery.setUserId(userCardListDTO.getUserId());
        userCardQuery.setBrand(userCardListDTO.getBrand());
        return userCardQuery;
    }
}
