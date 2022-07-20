package com.cindy.ocrdemo.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.cindy.ocrdemo.dto.UserLoginFormDTO;
import com.cindy.ocrdemo.pojo.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String JWT_SECRET = "FINTECH-BOC";

    // 单位秒
    public static final long JWT_TOKEN_VALIDITY = 5 * 24 * 60 * 60;

    /**
     * 创建token
     * @param user
     * @return
     */
    public static String createToken(User user) {

        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime expireTime = startTime.plusSeconds(JWT_TOKEN_VALIDITY);

        Map<String, Object> payload = new HashMap<String, Object>();
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, startTime);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, expireTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, startTime);
        //载荷
        payload.put("userName", user.getUsername());
        payload.put("userId", user.getUserId());

        String token = JWTUtil.createToken(payload, JWT_SECRET.getBytes());
        return token;
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static boolean verifyToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        return jwt.setKey(JWT_SECRET.getBytes()).verify();
    }

    /**
     * 获取token
     * @param token
     * @return
     */
    public static Long getUserIdTokenInfo(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        Long userId = Convert.toLong(jwt.getPayload("userId"), 0L);
        return userId;
    }

}
