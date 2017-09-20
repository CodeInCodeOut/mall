package com.dayuanit.shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.pay.domain.PayType;
import com.dayuanit.pay.service.PayService;
import com.dayuanit.shop.dto.AjaxResultDTO;
import com.dayuanit.shop.exception.ShopException;

@Controller
@RequestMapping("/pay")
public class PayController extends BaseController {
	
	private static final  Logger log = LoggerFactory.getLogger(PayController.class);
	
	@Autowired
	private PayService payService;
	
	@RequestMapping("/getPayChannle")
	@ResponseBody
	public AjaxResultDTO getPayChannle() {
		try {
			List<PayType> list = payService.listPayType();
			return AjaxResultDTO.success(list);
		} catch(ShopException se) {
			return AjaxResultDTO.failed(se.getMessage());
		} catch(Exception e) {
			log.error("获取支付渠道失败", e);
			return AjaxResultDTO.failed("获取支付渠道失败");
		}
		
	}

}
