package com.cindy.ocrdemo;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.Sign;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import com.cindy.ocrdemo.util.EncryptUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OcrDemoApplicationTests {

    @Test
    void contextLoads() {

        String psw = "123456";
        String s = EncryptUtil.encryptPassword(psw);
        System.out.println(s);
    }

}
