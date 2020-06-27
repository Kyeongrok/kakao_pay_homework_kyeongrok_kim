package com.kakao.pay.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Send {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer userId;
    private String roomId;
    private int price;
    private String token;
    private Boolean completed;
    private Integer receiver;

    public Send() {
    }

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Send(Integer xUserId, String xRoomId, int price, String token, Boolean completed) {
        this.userId = xUserId;
        this.roomId = xRoomId;
        this.price = price;
        this.token = token;
        this.completed = completed;
    }
}
