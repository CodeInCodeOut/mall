package com.dayuanit.shop.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.shop.dto.AjaxResultDTO;
import com.dayuanit.shop.exception.ShopException;
import com.dayuanit.shop.service.ProvinceAndCityService;

@Controller
@RequestMapping("/choiceAddress")
public class AddressController {
	
	private static final  Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private ProvinceAndCityService provinceAndCityService;
	
	@RequestMapping("/choiceProvince")
	@ResponseBody
	public AjaxResultDTO choiceProvince() {
		try {
			List<Map<String, String>> listProvince = provinceAndCityService.listProvince();
			return AjaxResultDTO.success(listProvince);
			
		} catch(ShopException se) {
			log.error("查询省 异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("查询省 异常 请联系客服");
		} catch (Exception e) {
			log.error("查询省 异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
	}
	
	
	@RequestMapping("/choiceCity")
	@ResponseBody
	public AjaxResultDTO choiceCity(String provincecode) {
		try {
			List<Map<String, String>> listCity = provinceAndCityService.listCity(provincecode);
			return AjaxResultDTO.success(listCity);
		} catch(ShopException se) {
			log.error("查询市 异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("查询市 异常 请联系客服");
		} catch (Exception e) {
			log.error("查询市 异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
	}
	
	@RequestMapping("/choiceArea")
	@ResponseBody
	public AjaxResultDTO choiceArea (String citycode) {
		try {
			List<Map<String, String>> listArea = provinceAndCityService.listArea(citycode);
			return AjaxResultDTO.success(listArea);
		} catch(ShopException se) {
			log.error("查询县区 异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("查询县区 异常 请联系客服");
		} catch (Exception e) {
			log.error("查询县区 异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
	}
	
	
	
}
