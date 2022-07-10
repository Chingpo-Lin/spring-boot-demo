package com.example.demo.utils;

import com.example.demo.model.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * Jwt工具类
 * 注意点：
 * 1. token generated, 可以通过base64解密
 * 2. base64解密出铭文信息，修改在编码，则失败
 * 3. 无法作为已颁布token，除非改密钥
 */
public class JWTUtils {

    /**
     * 过期时间 one week = 60000 * 60 * 24 * 7
     */
    private static final long EXPIRE = 60000 * 60 * 24 * 7;

    /**
     * 加密密钥
     */
    private static final String SECRET = "xdclass.net168";

    /**
     * 前缀
     */
    private static final String TOKEN_PREFIX = "online";

    /**
     * subject
     */
    private static final String SUBJECT = "class";

    public static String geneJsonWebToken(User user) {
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("head_img", user.getHeadImg())
                .claim("id", user.getId())
                .claim("name", user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();
        token = TOKEN_PREFIX + token;

        return token;
    }

    /**
     * 校验token的方法
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {

        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,"")).getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }
}
