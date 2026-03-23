package com.kaduox.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "kd0x8sF2mN9pQ4rT6vW1yZ3bC5eH7jK0mP2nL5qR8tU";
    private static Long expire = 43200000L;

    /**
     * 生成JWT令牌
     * @return
     */
    //创建工厂-设置签名算法和密钥-设置自定义载荷-设置过期时间-打包
    public static String generateJwt(Map<String,Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)                          // 1. 添加自定义载荷
                .signWith(SignatureAlgorithm.HS256, signKey) // 2. 设置签名算法和密钥
                .setExpiration(new Date(System.currentTimeMillis() + expire)) // 3. 设置有效期
                .compact();                                  // 4. 压缩成字符串
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)    // 1. 告知解密密钥
                .parseClaimsJws(jwt)       // 2. 解析并校验
                .getBody();                // 3. 获取数据内容
        return claims;
    }
}
