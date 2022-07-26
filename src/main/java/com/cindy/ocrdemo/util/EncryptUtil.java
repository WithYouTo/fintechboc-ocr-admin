package com.cindy.ocrdemo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptUtil {
	
	// 密码加密
    public static String encryptPassword(String password) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
    }
    
    // 比较验证，第一个参数为原始未加密的密码明文，第二个参数为数据库存储的密码hash值
    public static boolean matchPassword(String rawPassword, String encodedPassword) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	return passwordEncoder.matches(rawPassword, encodedPassword);
    }


}
