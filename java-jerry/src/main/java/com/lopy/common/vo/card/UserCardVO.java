package com.lopy.common.vo.card;

import lombok.Data;

@Data
public class UserCardVO {

    private Long id;
    private String brand;
    private String funding;
    private String lastFour;
    private Long expMonth;
    private Long expYear;
    private String country;
    private Integer primaryFlag;
}
