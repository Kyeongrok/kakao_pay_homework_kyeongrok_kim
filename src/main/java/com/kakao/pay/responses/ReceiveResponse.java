package com.kakao.pay.responses;

import lombok.*;

/*
0 성공
1 두번 이상 받을 수 없음
2 본인이 받을 수 없음
3 해당 RoomId, Token으로 등록된 이력이 없음
 */

@Getter
@Setter
public class ReceiveResponse {
    private Boolean success;
    private Integer code;
    private Integer price;
    private String reason;

    public ReceiveResponse(Boolean success, Integer code, String reason) {
        this.success = success;
        this.code = code;
        this.reason = reason;
    }

    public ReceiveResponse(Boolean success, Integer code, Integer price, String reason) {
        this.success = success;
        this.code = code;
        this.price = price;
        this.reason = reason;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
