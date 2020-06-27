package com.kakao.pay.repositories;

import com.kakao.pay.domain.Send;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SendRepository extends JpaRepository<Send, Long> {
    //
    List<Send> findByUserIdAndRoomId(Integer userId, String roomId);

    List<Send> findByRoomIdAndToken(String roomId, String token);

}
