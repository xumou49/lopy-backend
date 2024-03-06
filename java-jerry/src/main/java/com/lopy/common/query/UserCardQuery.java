package com.lopy.common.query;

import com.lopy.common.dto.card.UserCardDTO;
import lombok.Data;

@Data
public class UserCardQuery {

    private String brand;
    private Long userId;

    public static UserCardQuery build(UserCardDTO userCardDTO) {
        UserCardQuery userCardQuery = new UserCardQuery();
        userCardQuery.setUserId(userCardDTO.getUserId());
        userCardQuery.setBrand(userCardDTO.getBrand());
        return userCardQuery;
    }
}
