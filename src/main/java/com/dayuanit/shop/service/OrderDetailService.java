package com.dayuanit.shop.service;



import com.dayuanit.shop.dto.OrderDetailDTO;

public interface OrderDetailService {
	
	OrderDetailDTO listOrderDetail(Integer userId, Integer orderId);

}
