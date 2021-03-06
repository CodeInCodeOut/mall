package com.dayuanit.shop.service;

import java.util.List;
import java.util.Map;

import com.dayuanit.pay.dto.PayOrderUrlDTO;
import com.dayuanit.shop.domain.Order;
import com.dayuanit.shop.dto.BuyGoodsDto;
import com.dayuanit.shop.dto.OrderDTO;
import com.dayuanit.shop.dto.OrderGoodsDTO;
import com.dayuanit.shop.utils.PageUtils;
import com.dayuanit.shop.vo.BuyGoodsVo;
import com.dayuanit.shop.vo.OrderVo;

public interface OrderService {
	
	Order createOrder(List<BuyGoodsVo> vos, Integer userId);
	
	void createorder(OrderVo orderVo);
	
	List<BuyGoodsDto> createOrderFormCart(String buyMsg, Integer userId);
	
	List<BuyGoodsDto> createOrder4JsonBody(List<BuyGoodsVo> vos, Integer userId);
	
	OrderGoodsDTO listOrder(Integer orderId, Integer userId);
	
	PayOrderUrlDTO orderToPay(Integer orderId, Integer userId, Integer payChannel, Integer addressId);
	
	List<OrderDTO> listMyOrder(Integer userId, Integer status);
	
	List<OrderDTO> listMyOrder1(Integer userId, Integer status);
	
	PageUtils<OrderDTO> listMyOrder2(Integer userId, Integer status, Integer pageNum);
	
	void processPayresult(Integer orderId, Integer payId);
	
	PayOrderUrlDTO unPayOrderToPay(Integer userId, Integer orderId);
	
	Order nowCreateOrder(Integer userId, Integer goodsId, Integer goodsAccount);
	
	void rollBackRepertoryTimeOut(Order order);
	
	void changeOrderStatus(Integer userId, Integer orderId, Integer status);
	
	List<Order> listUnPayOrderForRollBack(Integer status);
	
	void deleteOrder(Integer status, Integer userId, Integer orderId);
	
	
	
}
