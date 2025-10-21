package com.cuber.lgfw.entity;

import lombok.Getter;

@Getter
public enum ShippingStatus {
    NOT_SHIPPED(0, "未发货"),
    PARTIALLY_SHIPPED(1, "部分发货"),
    SHIPPED(2, "已发货");

    private final int code;
    private final String description;

    ShippingStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
