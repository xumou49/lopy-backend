package com.lopy.service.apple;

import com.alibaba.fastjson.JSONObject;
import com.lopy.common.constant.RedisConstant;
import com.lopy.common.exception.ServiceException;
import com.lopy.common.exception.ValidationException;
import com.lopy.common.pojo.OAuth;
import com.lopy.common.utils.CollectionUtil;
import com.lopy.common.utils.JsonUtil;
import com.lopy.common.utils.MessageUtil;
import com.lopy.common.utils.StringUtil;
import com.lopy.config.app.LopyProps;
import com.lopy.service.biz.intf.AppleService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class AppleServiceImpl implements AppleService {

    @Value("${apple.issuer.id}")
    private String appleIssuer;

    @Value("${apple.auth-key.url}")
    private String appleAuthUrl;

    @Autowired
    private LopyProps lopyProps;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public OAuth verifyOauthToken(String identityToken) {
        Map<String, WebKey> keyMap = getAppleWebKeyMap();

        String[] args = identityToken.split("\\.");
        if (args.length != 3) {
            throw new ValidationException(MessageUtil.getMessage(""));
        }

        Map<String, String> headers = parseHeader(args[0]);
        String kid = headers.get("kid");
        if (StringUtil.isEmpty(kid)) {
            throw new ServiceException("");
        }

        // get the respective webkey
        WebKey webKey = keyMap.get(kid);
        if (webKey == null) {
            // possibly getting the old cache data, try fetch remotely again
            keyMap = getAppleWebKeyViaHttp();
            if ((webKey = keyMap.get(kid)) == null) {
                throw new ServiceException("");
            }
        }

        // get public key
        PublicKey publicKey = getPublicKey(webKey);
        if (publicKey == null) {
            throw new ServiceException("");
        }

        JwtParser jwt = Jwts.parserBuilder().setSigningKey(publicKey).build();
        Jws<Claims> claim = jwt.parseClaimsJws(identityToken);
        if (claim == null) {
            throw new ServiceException("");
        }

        String iss = claim.getBody().get("iss").toString();
        String aud = claim.getBody().get("aud").toString();
        Long exp = Long.parseLong(claim.getBody().get("exp").toString())*1000;
        if (!isValid(iss, aud, exp)) {
            throw new ServiceException("error");
        }

        String openId = claim.getBody().get("sub").toString();
        String email = claim.getBody().get("email").toString();
        return new OAuth(openId, email);
    }

    private PublicKey getPublicKey(WebKey webKey) {
        try {
            String n = webKey.getN();
            String e = webKey.getE();
            BigInteger modulus = new BigInteger(1, Base64.decodeBase64(n));
            BigInteger publicExponent = new BigInteger(1, Base64.decodeBase64(e));
            RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, publicExponent);
            KeyFactory kf = KeyFactory.getInstance(webKey.getKty());
            return kf.generatePublic(spec);
        } catch (Exception e) {
            throw new ValidationException("");
        }
    }

    private Map<String, String> parseHeader(String str) {
        String header = new String(Base64.decodeBase64(str));
        JSONObject jsonObject = JsonUtil.fromJson(header);
        return Map.of("kid", jsonObject.getString("kid"), "alg", jsonObject.getString("alg"));
    }

    private Map<String, WebKey> getAppleWebKeyMap() {
        // check if cache has the key first
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(RedisConstant.APPLE_WEB_KEYS);

        // if not, fetch remotely
        if (CollectionUtil.isEmpty(entries)) {
            return getAppleWebKeyViaHttp();
        }

        log.info("getting the key from cache ...");

        // if yes, parse & return it
        Map<String, WebKey> keyMap = new HashMap<>();
        entries.forEach((k, v) -> keyMap.put(String.valueOf(k), JsonUtil.fromJson(String.valueOf(v), WebKey.class)));
        return keyMap;
    }

    private Map<String, WebKey> getAppleWebKeyViaHttp() {
        log.info("getting the key remotely ...");
        JSONObject jsonObject = restTemplate.getForObject(appleAuthUrl, JSONObject.class);
        if (jsonObject == null) {
            throw new ServiceException("");
        }

        // clear old cache first
        redisTemplate.delete(RedisConstant.APPLE_WEB_KEYS);

        // save new data to cache
        List<WebKey> keys = jsonObject.getJSONArray("keys").toJavaList(WebKey.class);
        Map<String, WebKey> keyMap = new HashMap<>();
        for (WebKey key : keys) {
            redisTemplate.opsForHash().put(RedisConstant.APPLE_WEB_KEYS, key.getKid(), JsonUtil.toJson(key));
            keyMap.put(key.getKid(), key);
        }
        return keyMap;
    }

    private boolean isValid(String iss, String aud, Long exp) {
        return System.currentTimeMillis() < exp && Objects.equals(iss, appleIssuer) && Objects.equals(aud, lopyProps.getAppId());
    }
}
