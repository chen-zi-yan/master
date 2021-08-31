package com.hnly.provincial.comm.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hnly.provincial.entity.user.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author maqh
 * @version 1.0
 * @date 2021-08-31
 */
@Slf4j
public class TokenUtil {

    private static final long EXPIRE_TIME = 60L * 60L * 1000L;
    //密钥盐
    private static final String TOKEN_SECRET = "hnly";

    /**
     * 签名生成
     *
     * @param user 用户
     * @return 签名
     */
    public static String sign(User user) {
        Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create().withIssuer("auth0")
                .withClaim("userName", user.getUsername())
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC256(TOKEN_SECRET));
    }


    /**
     * 签名验证
     *
     * @param token 签名验证
     * @return 结果
     */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            log.debug("issuer:{}", jwt.getIssuer());
            log.debug("userName:{}", jwt.getClaim("userName").asString());
            log.debug("过期时间:{}", jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
