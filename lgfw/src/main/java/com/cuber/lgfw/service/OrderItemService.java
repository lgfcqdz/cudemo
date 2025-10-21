package com.cuber.lgfw.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cuber.lgfw.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemService extends IService<OrderItem> {
    List<OrderItem> getItemsByOrderId(Long orderId);

    BigDecimal calculateOrderTotal(Long orderId);

    boolean updateShippingStatus(Long itemId, Integer shippedQuantity);
}
