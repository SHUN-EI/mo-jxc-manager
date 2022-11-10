package com.mo.db;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by mo on 2022/11/10
 */
public class EncoderTest {

    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("123");
        System.out.println("加密后的字符串为:"+encode);
    }
}
