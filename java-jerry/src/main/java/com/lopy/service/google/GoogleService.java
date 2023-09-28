package com.lopy.service.google;

import com.lopy.common.pojo.OAuth;

public interface GoogleService {

    OAuth verifyOauthToken(String token);
}