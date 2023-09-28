package com.lopy.service.google;

import com.lopy.common.exception.ServiceException;
import com.lopy.common.pojo.OAuth;
import com.lopy.common.utils.JsonUtil;
import com.lopy.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class GoogleServiceImpl implements GoogleService {

    @Value("${google.api.domain}")
    private String googleApiDomain;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public OAuth verifyOauthToken(String token) {
        String url = String.format("%s/oauth2/v2/userinfo?access_token=%s", googleApiDomain, token);
        String response = doGetRequest(url);
        if (StringUtil.isEmpty(response)) {
            throw new ServiceException("");
        }
        return JsonUtil.fromJson(response, OAuth.class);
    }

    private String doGetRequest(String url) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        } catch (Exception e) {
            log.error("requestToApi invokes exception, error", e);
            return "";
        }
    }
}
