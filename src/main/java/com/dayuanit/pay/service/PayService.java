package com.dayuanit.pay.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.dayuanit.pay.domain.PayOrder;
import com.dayuanit.pay.domain.PayType;
import com.dayuanit.pay.dto.PayOrderUrlDTO;

@WebService
public interface PayService {
	
	PayOrderUrlDTO addPayOrder(PayOrder payOrder);
	
	List<PayType> listPayType();
	
	String getPayName(int payType);
	
	PayOrder getPayOrder(int payId);
	
}
