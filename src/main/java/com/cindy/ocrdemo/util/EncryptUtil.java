package com.cindy.ocrdemo.util;

import org.apache.commons.codec.digest.Md5Crypt;

public class EncryptUtil extends Md5Crypt {

    private static final String SALT = "$1$BOC-FINTECH-SALT";

    /**
     * md5加盐加密
     * @param password
     * @return
     */
    public static String encryptPassword(String password) {
        return EncryptUtil.md5Crypt(password.getBytes(), SALT);
    }


}
