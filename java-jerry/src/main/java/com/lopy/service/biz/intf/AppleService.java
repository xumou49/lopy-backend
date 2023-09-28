package com.lopy.service.biz.intf;

import com.lopy.common.pojo.OAuth;

public interface AppleService {

    OAuth verifyOauthToken(String token);
}
