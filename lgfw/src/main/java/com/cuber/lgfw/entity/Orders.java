package com.cuber.lgfw.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("`orders`")  // 注意：order 是关键字，需要用反引号
public class Orders {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String orderNumber;
    private Long userId;
    private BigDecimal totalAmount;
    private BigDecimal discountAmount;
    private BigDecimal shippingFee;
    private BigDecimal actualAmount;

    // 状态字段
    private Integer orderStatus;
    private Integer paymentStatus;
    private Integer deliveryStatus;

    // 支付信息
    private String paymentMethod;
    private String paymentNumber;
    private LocalDateTime paymentTime;
    private BigDecimal paymentAmount;

    // 配送信息
    private String shippingAddress;
    private String receiverName;
    private String receiverPhone;
    private String shippingCompany;
    private String trackingNumber;
    private LocalDateTime shippingTime;
    private LocalDateTime expectedDeliveryTime;

    // 时间信息
    private LocalDateTime orderTime;
    private LocalDateTime payDeadlineTime;
    private LocalDateTime completedTime;
    private LocalDateTime cancelTime;
    private LocalDateTime refundTime;

    // 系统字段
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;
}
