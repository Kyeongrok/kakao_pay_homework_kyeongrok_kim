package com.kakao.pay.controllers;

import com.kakao.pay.components.Distributer;
import com.kakao.pay.components.Receiver;
import com.kakao.pay.maker.TokenMaker;
import com.kakao.pay.responses.ReceiveResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;

@RestController
@RequestMapping(value = "/api/v1/order")
public class DistributeController {

    @Autowired
    private Distributer distributer;

    @Autowired
    private Receiver receiver;

    // 뿌리기
    @GetMapping(value = "/distribute")
    public ResponseEntity<String> receiveDistributeRequest(
            @RequestHeader("X-USER-ID") Integer x_user_id,
            @RequestHeader("X-ROOM-ID") String x_room_id,
            @RequestParam int price, @RequestParam int peopleCount) {

        String token = new TokenMaker().make();

        // error가 나면 error가 났다고 response해준다.
        try {
            // 발급해주고 인원수 만큼 db를 등록한다.
            distributer.distribute(x_user_id, x_room_id, price, peopleCount, token);
        }catch(Exception e){
            return new ResponseEntity<>(
                    "fail to create distribute",
                    HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(
                token,
                HttpStatus.OK);
    }

    // 받기
    @GetMapping(value = "/receive")
    public ResponseEntity<String> receive(
            @RequestHeader("X-USER-ID") Integer x_user_id,
            @RequestHeader("X-ROOM-ID") String x_room_id,
            @RequestParam String token
    ){

        ReceiveResponse receiveResponse = this.receiver.receive(token, x_user_id, x_room_id);
        if(receiveResponse.getSuccess()){
            return new ResponseEntity<>(
                    receiveResponse.getPrice().toString(),
                    HttpStatus.OK);
        }else{
            return new ResponseEntity<>(
                    receiveResponse.getReason(),
                    HttpStatus.EXPECTATION_FAILED);
        }
    }
}
