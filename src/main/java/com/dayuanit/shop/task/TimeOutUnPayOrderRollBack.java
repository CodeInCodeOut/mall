package com.dayuanit.shop.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayuanit.shop.Enum.OrderStatusEnum;
import com.dayuanit.shop.domain.Order;
import com.dayuanit.shop.mapper.OrderMapper;
import com.dayuanit.shop.service.OrderService;

@Component("timeOutUnPayOrderRollBack")
public class TimeOutUnPayOrderRollBack {
	
	private static final Logger log = LoggerFactory.getLogger(TimeOutUnPayOrderRollBack.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	public void doTask() {
		log.info("---------------doTask----------------");
		
		List<Order> listUnPayOrder = null;
		for (int startNum = 0; ; startNum+=20){
			log.info("---------------startNum{}----------------",startNum);
			
			Integer orderSize = 20;
			listUnPayOrder = orderMapper.listMyOrderPage(null, OrderStatusEnum.UNPAY.getCode(), startNum, orderSize);
			if (listUnPayOrder.size() <= 0) {
				break;
			}
			
		}
		
//		while(true) {
//			Integer orderSize = 20;
//			Integer startNum = 0;
//			listUnPayOrder = orderMapper.listMyOrderPage(null, OrderStatusEnum.UNPAY.getCode(), startNum, orderSize);
//			if (listUnPayOrder.size() <= 0) {
//				break;
//			}
//			startNum += 20;
//		}
		
		//List<Order> listUnPayOrder = orderService.listUnPayOrderForRollBack(OrderStatusEnum.UNPAY.getCode());
		for ( Order order : listUnPayOrder ) {
			
			try {
				Date orderTime = order.getModifyTime();
				Calendar cal = Calendar.getInstance();
				cal.setTime(orderTime);
				cal.add(Calendar.MINUTE, 30);
				
				Date expTime = cal.getTime();
				
				if (new Date().after(expTime)) {
					orderService.rollBackRepertoryTimeOut(order);
					log.info(">>>>>处理订单{}", order.getId());
				}
					
			} catch (Exception e) {
				log.error("处理过期订单失败异常信息{}", e.getMessage(), e);
				continue;
			}
			
		}
		
	}
	
	

}
