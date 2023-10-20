package com.boris.kata312;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class Encoding {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static void main(String[] args) {

        System.out.println(bCryptPasswordEncoder.encode("a"));
    }
}
