package com.cuber.lgfw.tests.unit.service;

import com.cuber.lgfw.entity.OrderStatus;
import com.cuber.lgfw.entity.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/***
 * 基於狀態的測試
 */
@SpringBootTest
public class OrderServiceTest {

//    @Test
//    void cancelOrder_FromPendingState_ShouldCancelSuccessfully() {
//        // 初始状态：PENDING
//        Orders order = createOrderWithStatus(OrderStatus.PENDING);
//
//        orderService.cancelOrder(order.getId());
//
//        // 最终状态：CANCELLED
//        Orders updated = orderRepository.findById(order.getId());
//        assertEquals(OrderStatus.CANCELLED, updated.getStatus());
//    }
//
//    @Test
//    void cancelOrder_FromShippedState_ShouldFail() {
//        // 状态转换测试：SHIPPED -> CANCELLED 应该失败
//        Orders order = createOrderWithStatus(OrderStatus.SHIPPED);
//
//        assertThrows(IllegalStateException.class,
//                () -> orderService.cancelOrder(order.getId()));
//    }
}
