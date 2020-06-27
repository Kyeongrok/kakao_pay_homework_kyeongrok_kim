package com.kakao.pay.maker;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenMakerTest {
    @Test
    public void make() {
        TokenMaker tm = new TokenMaker();
        Assert.assertTrue(3 == tm.make().length());
    }
}