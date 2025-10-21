package com.cuber.lgfw.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuber.lgfw.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface  OrderItemMapper extends BaseMapper<OrderItem> {
    // 根据订单ID查询订单项
    @Select("SELECT * FROM order_item WHERE order_id = #{orderId} AND deleted = 0")
    List<OrderItem> selectByOrderId(Long orderId);

    // 查询订单项统计信息
    @Select("SELECT product_id, SUM(quantity) as total_sold " +
            "FROM order_item WHERE created_time BETWEEN #{startTime} AND #{endTime} " +
            "GROUP BY product_id")
    List<Map<String, Object>> selectSalesStatistics(String startTime, String endTime);
}
