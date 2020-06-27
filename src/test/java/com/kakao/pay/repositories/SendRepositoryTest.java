package com.kakao.pay.repositories;

import com.kakao.pay.components.Distributer;
import com.kakao.pay.domain.Send;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SendRepositoryTest {
    @Autowired
    private SendRepository sendRepository;

    @Autowired
    private Distributer distributer;

    @Test
    public void savdAndFindAllTest() {
        Send send = new Send();
        this.sendRepository.save(send);
        List<Send> sends = this.sendRepository.findAll();
        assertEquals(1, sends.size());
    }


    @Test
    public void findTargetRows() {

        String roomId = "room1";
        String token = "abc";

        this.distributer.distribute(111, roomId, 123, 4, token);
        this.distributer.distribute(111, "room2", 123, 4, "bcd");

        List<Send> sends = this.sendRepository.findByRoomIdAndToken(roomId, token);

        Assert.assertTrue(4 == sends.size());

    }
}