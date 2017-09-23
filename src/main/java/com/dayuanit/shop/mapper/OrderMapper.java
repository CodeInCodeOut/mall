package com.dayuanit.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.shop.domain.Order;

public interface OrderMapper {

	int createOrder(Order order);
	
	int updateOrderToPay(Order order);
	
	int changeOrderStatus(@Param("orderId")Integer orderId, @Param("userId")Integer userId, @Param("status")Integer status);
	
	Order getOrderById(@Param("orderId")Integer orderId, @Param("userId")Integer userId);
	
	List<Order> listOrderByUserIdAndStatus(@Param("userId")Integer userId, @Param("status")Integer status);
	
	List<Order> listMyOrderByUserIdAndStatus(@Param("userId")Integer userId, @Param("status")Integer status);
	
	List<Order> listMyOrderPage(@Param("userId")Integer userId,
								@Param("status")Integer status,
								@Param("startNum")Integer startNum,
								@Param("orderSize")Integer orderSize);
	
	int getOrderTotal(@Param("userId")Integer userId, @Param("status")Integer status);
	
	Order getOrderForUpdate(Integer orderId);
	
}
