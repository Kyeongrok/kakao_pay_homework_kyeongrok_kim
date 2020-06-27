package com.kakao.pay.components;

import com.kakao.pay.domain.Send;
import com.kakao.pay.repositories.SendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Distributer {

    @Autowired
    private SendRepository sendRepository;

    public void distribute(Integer xUserId, String xRoomId, int price, int peopleCount, String token){
        int eachPrice = price / peopleCount;
        int remainder = price % peopleCount;

        // 생성해서 저장한다.
        ArrayList<Send> sends = new ArrayList<>();
        for (int i = 0 ; i < peopleCount ; i+=1){
            if(i == peopleCount - 1)
                eachPrice += remainder;

            Send send = new Send(xUserId, xRoomId, eachPrice, token, false);

            sends.add(send);
        }

        sendRepository.saveAll(sends);
    }
}
