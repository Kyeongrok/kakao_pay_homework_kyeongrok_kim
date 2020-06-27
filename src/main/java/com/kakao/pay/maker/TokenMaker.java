package com.kakao.pay.maker;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TokenMaker {
    public String make(){
        String token = "";
        for (int i = 0; i < 3 ; i+=1){
            char c = (char)(new Random().nextInt(26) + 'a');
            token += c;
        }
        return token;
    }
}
