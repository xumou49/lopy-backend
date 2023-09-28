package com.lopy.service.apple;

import com.lopy.common.pojo.OAuth;

public interface AppleService {

    OAuth verifyIdentifyToken(String token);
}
