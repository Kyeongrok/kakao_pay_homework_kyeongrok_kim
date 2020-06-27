package com.kakao.pay.components;

import com.kakao.pay.domain.Send;
import com.kakao.pay.repositories.SendRepository;
import com.kakao.pay.responses.ReceiveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Receiver {
    @Autowired
    private SendRepository sendRepository;

    public ReceiveResponse receive(String token, Integer xUserId, String xRoomId){
        List<Send> sends = this.sendRepository.findByRoomIdAndToken(xRoomId, token);
        if (sends.size() == 0) {
            return new ReceiveResponse(false, 3, "해당 RoomId, Token으로 등록된 이력이 없음");
        }

        System.out.println("size:" +sends.size());
        // 한 사용자는 한번만 받을 수 있음
        List<Integer> checkDuplicate = sends.stream()
                .map(send -> send.getReceiver())
                .collect(Collectors.toList());

        if(checkDuplicate.indexOf(xUserId) > 0) {
            return new ReceiveResponse(false, 1, "두번 이상 받을 수 없음");
        }

        // 1개를 선택해서 true 처리 한다.
        Send send = sends.get(0);
        if (send.getUserId().equals(xUserId)) {
            return new ReceiveResponse(false, 2, "본인이 받을 수 없음");
        }

        send.setCompleted(true);
        send.setReceiver(xUserId);
        send.setPrice(send.getPrice());
        this.sendRepository.save(send);

        return new ReceiveResponse(true, 0, send.getPrice(), "ok");

    }
}
