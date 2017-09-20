package com.dayuanit.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.shop.domain.OrderDetail;

public interface OrderDetailMapper {
	
	int saveOrderDetail(OrderDetail orderDetail);
	
	List<OrderDetail> listOrderDetail(@Param("orderId")Integer orderId);
	
}
