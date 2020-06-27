package com.kakao.pay.components;

import com.kakao.pay.domain.Send;
import com.kakao.pay.repositories.SendRepository;
import com.kakao.pay.responses.ReceiveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Receiver {
    @Autowired
    private SendRepository sendRepository;

    public ReceiveResponse receive(String token, Integer xUserId, String xRoomId){
        ReceiveResponse receiveResponse = new ReceiveResponse();
        List<Send> sends = this.sendRepository.findByRoomIdAndToken(xRoomId, token);
        if (sends.size() == 0) {
            receiveResponse.setSuccess(false);
            receiveResponse.setReason("해당 RoomId, Token으로 등록된 이력이 없음");
            return receiveResponse;
        }

        // 한 사용자는 한번만 받을 수 있음
        if(sends.stream().filter(send -> send.getReceiver().equals(xUserId)).findAny().isPresent()) {
            receiveResponse.setSuccess(false);
            receiveResponse.setReason("두번 이상 받을 수 없음");
            return receiveResponse;
        }

        // 1개를 선택해서 true 처리 한다.
        Send send = sends.get(0);
        if (send.getUserId().equals(xUserId)) {
            receiveResponse.setSuccess(false);
            receiveResponse.setReason("본인이 받을 수 없음");
            return receiveResponse;
        }

        send.setCompleted(true);
        send.setReceiver(xUserId);
        send.setPrice(send.getPrice());
        this.sendRepository.save(send);

        return receiveResponse;

    }
}
