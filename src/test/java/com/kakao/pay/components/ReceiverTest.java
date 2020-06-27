package com.kakao.pay.components;

import com.kakao.pay.repositories.SendRepository;
import com.kakao.pay.responses.ReceiveResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReceiverTest {

    @Autowired
    private Distributer distributer;
    @Autowired
    private Receiver receiver;
    @Autowired
    private SendRepository sendRepository;

    @Test
    public void receive() {

        int peopleCount = 4;
        this.distributer.distribute(111, "room1", 123, peopleCount, "abc");

        ReceiveResponse receiveResponse = receiver.receive("abc", 111, "room1");

        System.out.println(receiveResponse.getReason());
    }
}