package com.dayuanit.shop.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.shop.domain.Order;
import com.dayuanit.shop.domain.OrderDetail;
import com.dayuanit.shop.dto.OrderDetailDTO;
import com.dayuanit.shop.dto.OrderDetailDTO.OrderGoods;
import com.dayuanit.shop.exception.ShopException;
import com.dayuanit.shop.mapper.OrderDetailMapper;
import com.dayuanit.shop.mapper.OrderMapper;
import com.dayuanit.shop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public OrderDetailDTO listOrderDetail(Integer userId, Integer orderId) {
		
		Order order = orderMapper.getOrderById(orderId, userId);
		if (null == order) {
			throw new ShopException("订单为空 --订单详情--有误");
		}
		
		OrderDetailDTO odd = new OrderDetailDTO();
		
		odd.setAreaName(order.getAreaName());
		odd.setCityName(order.getCityName());
		odd.setDeliveryTime("全天送单");
		odd.setDtaAddress(order.getDtaAddress());
		odd.setFreight("0");
		odd.setId(order.getId());
		odd.setPayChannel(order.getPayChannel());
		odd.setPhone(order.getPhone());
		odd.setProvinceName(order.getProvinceName());
		odd.setTotalPrice(order.getTotalPrice());
		odd.setStatus(order.getStatus());
		odd.setUserRealName(order.getUserRealName());
		//odd.setDisbursements(String.valueOf(Integer.parseInt(odd.getFreight()) + Integer.parseInt(odd.getFreight())));
		List<OrderDetail> listOrderDetail = orderDetailMapper.listOrderDetail(orderId);
		List<OrderGoods> orderGoods = new ArrayList<OrderGoods>(listOrderDetail.size());
		OrderGoods og = new OrderGoods();
		for (OrderDetail od : listOrderDetail) {
			
			og.setGoodsAccount(od.getGoodsAccount());
			og.setGoodsName(od.getGoodsName());
			og.setPrice(od.getPrice());
			orderGoods.add(og);
		}
		
		odd.setOrderGoods(orderGoods);
		
		return odd;
	}

}
