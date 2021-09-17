package com.hnly.provincial.comm.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hnly.provincial.comm.ResultEnum;
import com.hnly.provincial.config.interceptor.exception.MyException;
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

    public static final long EXPIRE_TIME = 60L * 60L * 1000L;
    //密钥盐
    private static final String TOKEN_SECRET = "hnly";

    private static final String AUTH = "auth0";

    private static final String USERNAME = "userName";
    private static final String ID = "id";
    private static final String ROLEID = "roleId";

    /**
     * 签名生成
     *
     * @param user 用户
     * @return 签名
     */
    public static String sign(User user) {
        Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return JWT.create()
                .withIssuer(AUTH)
                .withClaim(USERNAME, user.getUsername())
                .withClaim(ID, user.getId())
                .withClaim(ROLEID,user.getQuanxian())
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
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer(AUTH).build();
            DecodedJWT jwt = verifier.verify(token);
            if (System.currentTimeMillis() > jwt.getExpiresAt().getTime()) {
                throw new MyException(ResultEnum.TOKEN_EXPIRED);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新token
     *
     * @param token token
     * @return 刷新后token
     */
    public static String refreshToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer(AUTH).build();
        DecodedJWT jwt = verifier.verify(token);
        return JWT.create()
                .withIssuer(AUTH)
                .withClaim(USERNAME, jwt.getClaim(USERNAME).asString())
                .withClaim(ID, jwt.getClaim(ID).asLong())
                .withClaim(ROLEID,jwt.getClaim(ROLEID).asLong())
                .withExpiresAt(new Date(jwt.getExpiresAt().getTime() + EXPIRE_TIME))
                .sign(Algorithm.HMAC256(TOKEN_SECRET));
    }

    /**
     * 获取token中的用户id
     *
     * @param token token
     * @return 用户id
     */
    public static User getTokenUserId(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer(AUTH).build();
        DecodedJWT jwt = verifier.verify(token);
        User user = new User();
        user.setId(jwt.getClaim(ID).asLong());
        user.setQuanxian(jwt.getClaim(ROLEID).asLong());
        return user;
    }
}
