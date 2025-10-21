package com.cuber.lgfw.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("order_item")
public class OrderItem {
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long orderId;
    private Long productId;

    // 商品快照信息
    private String productSnapshot; // JSON格式
    private String productName;
    private String productImage;
    private String productSku;
    private String productSpecs;

    // 价格信息
    private BigDecimal originalPrice;
    private BigDecimal actualPrice;
    private Integer quantity;
    private BigDecimal subtotal;
    private BigDecimal discountAmount;

    // 库存和物流
    private Long warehouseId;
    private Integer shippingStatus;
    private Integer shippedQuantity;

    // 售后信息
    private Integer refundStatus;
    private Integer refundQuantity;
    private BigDecimal refundAmount;

    // 评价信息
    private Integer reviewStatus;

    // 时间信息
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    // 系统字段
    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;
}
