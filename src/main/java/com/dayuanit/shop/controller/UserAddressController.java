package com.dayuanit.shop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.shop.domain.UserAddress;
import com.dayuanit.shop.dto.AjaxResultDTO;
import com.dayuanit.shop.exception.ShopException;
import com.dayuanit.shop.service.ProvinceAndCityService;
import com.dayuanit.shop.service.UserAddressService;
import com.dayuanit.shop.vo.UserAddressVo;

@Controller
@RequestMapping("/userAddress")
public class UserAddressController {
	
	private static final  Logger log = LoggerFactory.getLogger(UserAddressController.class);
	
	@Autowired
	private UserAddressService userAddressService;
	
	@Autowired
	private ProvinceAndCityService provinceAndCityService;
	
	@RequestMapping("/addUserAddress")
	@ResponseBody
	public AjaxResultDTO addUserAddress(UserAddressVo userAddressVo ) {
		
		
		try {
			userAddressService.addAddress(userAddressVo);
			return AjaxResultDTO.success();
		} catch(ShopException se) {
			log.error("增加用户收货地址  异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("增加用户收货地址  异常");
		} catch (Exception e) {
			log.error("增加用户收货地址 异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
	}
	
	
	@RequestMapping("/listUserAddress")
	@ResponseBody
	public AjaxResultDTO listUserAddress () {
		Integer userId = 1;
		try {
			List<UserAddress> listUserAddress = userAddressService.listUserAddressByUserId(userId);
			return AjaxResultDTO.success(listUserAddress);
		} catch(ShopException se) {
			log.error("遍历用户收货地址  异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("遍历用户收货地址  异常");
		} catch (Exception e) {
			log.error("遍历用户收货地址 异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
	}
	
	@RequestMapping("/modifyAddress")
	@ResponseBody
	public AjaxResultDTO modifyAddress(UserAddressVo userAddressVo) {
		
		UserAddress userAddress = new UserAddress();
		log.info("userAddressVo.getCityCode()", userAddressVo.getCityCode());
		userAddress.setAreaCode(userAddressVo.getAreaCode());
//		String areaName = provinceAndCityService.getArea(userAddressVo.getAreaCode());
//		userAddressVo.setAreaName(areaName); 把vo 写到后台service 做业务校验处理  
		userAddress.setAreaName(userAddressVo.getAreaName());
		
		userAddress.setCityCode(userAddressVo.getCityCode());
//		String cityName = provinceAndCityService.getCity(userAddressVo.getCityCode());
//		userAddressVo.setCityName(cityName);
		userAddress.setCityName(userAddressVo.getCityName());
		
		userAddress.setProvinceCode(userAddressVo.getProvinceCode());
//		String provinceName = provinceAndCityService.getProvince(userAddressVo.getProvinceCode());
//		userAddressVo.setProvinceName(provinceName);
		userAddress.setProvinceName(userAddressVo.getProvinceName());
		
		userAddress.setId(userAddressVo.getId());
		userAddress.setDtaAddress(userAddressVo.getDtaAddress());
		userAddress.setUserRealName(userAddressVo.getUserRealName());
		userAddress.setPhone(userAddressVo.getPhone());
		userAddress.setIsDefauAdress(userAddressVo.getIsDefauAdress());
		
		try {
			userAddressService.modifyAddress(userAddress);
			return AjaxResultDTO.success();
		} catch(ShopException se) {
			log.error("修改用户收货地址  异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("修改用户收货地址  异常");
		} catch (Exception e) {
			log.error("修改用户收货地址 异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		} 
		
	}
	
	
	@RequestMapping("/deleteAddress")
	@ResponseBody
	public AjaxResultDTO deleteAddress(Integer addressId) {
		Integer userId = 1;
		try {
			log.info("{}", addressId);
			userAddressService.deleteAddress(userId, addressId);
			return AjaxResultDTO.success();
		} catch(ShopException se) {
			log.error("删除用户收货地址  异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("删除用户收货地址  异常");
		} catch (Exception e) {
			log.error("删除用户收货地址 异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		} 
		
	}
	

}
