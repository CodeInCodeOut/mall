package com.dayuanit.shop.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayuanit.shop.domain.Order;
import com.dayuanit.shop.service.OrderService;

@Component("timeOutUnPayOrderRollBack")
public class TimeOutUnPayOrderRollBack {
	
	private static final Logger log = LoggerFactory.getLogger(TimeOutUnPayOrderRollBack.class);
	
	@Autowired
	private OrderService orderService;
	
	
	public void doTask() {
		Integer userId = 1;
		Integer status = 1;
		List<Order> listUnPayOrder = orderService.listEffectivedOrder(userId, status);
		for(Order od : listUnPayOrder) {
			log.error("---------------doTask----------------");
			try {
				orderService.rollBackRepertoryTimeOut(od);
			} catch (Exception e) {
				log.error("异常信息{}", e.getMessage(), e);
				continue;
			}
			
			
		}
		
	}

}
