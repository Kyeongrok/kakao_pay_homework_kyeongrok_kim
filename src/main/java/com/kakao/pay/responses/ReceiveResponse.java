package com.kakao.pay.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveResponse {
    private Boolean success;
    private Integer code;
    private Integer price;
    private String reason;
}
