package com.cuber.lgfw.entity;

import lombok.Getter;

@Getter
public enum RefundStatus {
    NO_REFUND(0, "无退款"),
    REFUNDING(1, "退款中"),
    REFUNDED(2, "已退款");

    private final int code;
    private final String description;

    RefundStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
