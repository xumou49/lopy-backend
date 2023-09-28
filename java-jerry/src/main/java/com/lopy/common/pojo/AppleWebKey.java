package com.lopy.common.pojo;

import lombok.Data;

@Data
public class AppleWebKey {

    private String alg;
    private String e;
    private String kid;
    private String kty;
    private String n;
    private String use;
}
