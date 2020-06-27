package com.kakao.pay.components;

import com.kakao.pay.domain.Send;
import com.kakao.pay.repositories.SendRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
class DistributerTest {
    @Autowired
    private Distributer distributer;

    @Autowired
    private SendRepository sendRepository;

    @Test
    public void distributeTest() {
        // peopleCount만큼 row가 생성 되는지
        int peopleCount = 4;
        this.distributer.distribute(111, "rr", 123, peopleCount, "abc");
        List<Send> sends = sendRepository.findAll();
        Assert.assertTrue(peopleCount == sends.size());

        this.distributer.distribute(111, "room2", 123, 4, "bcd");
        sends = sendRepository.findAll();
        Assert.assertTrue(8 == sends.size());

    }

}