package com.lopy.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTPayload {

    private Long userId;
    private String userType;
}
