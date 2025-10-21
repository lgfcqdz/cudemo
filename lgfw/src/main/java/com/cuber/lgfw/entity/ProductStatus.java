package com.cuber.lgfw.entity;

import lombok.Getter;

@Getter
public enum ProductStatus {
    ON_SALE(1, "上架"),
    OFF_SALE(0, "下架"),
    PENDING_REVIEW(2, "待审核");

    private final int code;
    private final String description;

    ProductStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
