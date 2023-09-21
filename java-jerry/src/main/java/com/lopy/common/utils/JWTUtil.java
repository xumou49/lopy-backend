package com.lopy.common.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lopy.common.pojo.JWTPayload;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@Slf4j
public class JWTUtil {

    private JWTUtil() {}

    /**
     * expiration in 1 day
     */
    private static final long EXPIRE_TIME = 60 * 24 * 1000L;

    /**
     * signing secret
     */
    private static final String TOKEN_SECRET = "loopyGo2023!";

    /**
     * JWT token header
     */
    private static final Map<String, Object> header = Map.of("alg", "HS256", "typ", "JWT");

    public static String createToken(Map<String, String> claims) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTCreator.Builder token = JWT.create()
                    .withHeader(header)
                    .withExpiresAt(date);
            claims.forEach(token::withClaim);
            return token.sign(algorithm);
        } catch (Exception e) {
            log.error("JWTUtil invokes createToken error: {}", e.getMessage());
            return null;
        }
    }

    public static JWTPayload verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String userId = jwt.getClaim("userId").asString();
            String userType = jwt.getClaim("userType").asString();
            return new JWTPayload(DataUtil.toLong(userId), userType);
        } catch (Exception e){
            log.error("JWTUtil invokes verifyToken error: {}", e.getMessage());
            return null;
        }
    }
}
