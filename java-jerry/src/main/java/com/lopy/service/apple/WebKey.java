package com.lopy.service.apple;

import lombok.Data;

@Data
class WebKey {
    private String alg;
    private String e;
    private String kid;
    private String kty;
    private String n;
    private String use;
}
