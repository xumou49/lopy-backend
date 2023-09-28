package com.lopy.common.dto.auth;

import lombok.Data;

@Data
public class LoginDTO {

    /**
     * used for phone sign in
     */
    private String phone;

    /**
     * used for apple / google sign in
     */
    private String token;
}
