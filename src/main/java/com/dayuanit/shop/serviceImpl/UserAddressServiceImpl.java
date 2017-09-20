package com.dayuanit.shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.shop.domain.UserAddress;
import com.dayuanit.shop.exception.ShopException;
import com.dayuanit.shop.mapper.UserAddressMapper;
import com.dayuanit.shop.service.ProvinceAndCityService;
import com.dayuanit.shop.service.UserAddressService;
import com.dayuanit.shop.vo.UserAddressVo;

@Service
public class UserAddressServiceImpl implements UserAddressService{
	
	@Autowired
	private UserAddressMapper userAddressMapper;
	
	@Autowired
	private ProvinceAndCityService provinceAndCityService;
	

	@Override
	public void addAddress(UserAddressVo userAddressVo) {
		// TODO Auto-generated method stub
		
		UserAddress userAddress = new UserAddress();
		
		userAddress.setAreaCode(userAddressVo.getAreaCode());
		String areaName = provinceAndCityService.getArea(userAddressVo.getAreaCode());
		if (null == areaName) {
			throw new ShopException("areaName null 客户增加地址到地址表失败");
		}
		userAddressVo.setAreaName(areaName); 
		userAddress.setAreaName(userAddressVo.getAreaName());
		
		userAddress.setCityCode(userAddressVo.getCityCode());
		String cityName = provinceAndCityService.getCity(userAddressVo.getCityCode());
		if (null == cityName) {
			throw new ShopException("cityName null 客户增加地址到地址表失败");
		}
		userAddressVo.setCityName(cityName);
		userAddress.setCityName(userAddressVo.getCityName());
		
		userAddress.setProvinceCode(userAddressVo.getProvinceCode());
		String provinceName = provinceAndCityService.getProvince(userAddressVo.getProvinceCode());
		if (null == provinceName) {
			throw new ShopException("provinceName null 客户增加地址到地址表失败");
		}
		userAddressVo.setProvinceName(provinceName);
		userAddress.setProvinceName(userAddressVo.getProvinceName());
		
		userAddress.setDtaAddress(userAddressVo.getDtaAddress());
		userAddress.setUserRealName(userAddressVo.getUserRealName());
		Integer userId = 1;
		userAddress.setUserId(userId);
		userAddress.setPhone(userAddressVo.getPhone());
		userAddress.setIsDefauAdress(userAddressVo.getIsDefauAdress());
		
		int rows = userAddressMapper.addUserAddress(userAddress);
		
		if (1 != rows) {
			throw new ShopException("客户增加地址到地址表失败");
		}
		
	}

	@Override
	public List<UserAddress> listUserAddressByUserId(Integer userId) {
		// TODO Auto-generated method stub
		
		List<UserAddress> listUserAddress = userAddressMapper.listUserAddressByUserId(userId);
		
		if (null == listUserAddress) {
			throw new ShopException("查询客户地址表所有的收货地址失败");
		}
		
		return listUserAddress;
	}

	@Override
	public void modifyAddress(UserAddress userAddress) {
		// TODO Auto-generated method stub
		
		UserAddress ua = userAddressMapper.getUserAddress(userAddress.getUserId(), userAddress.getId());
		
		if (null == ua) {
			throw new ShopException("客户修改地址到地址表查询校验地址信息失败");
		}
		
		if (ua.getId().intValue() != userAddress.getId().intValue()) {
			throw new ShopException("客户修改地址到地址表查询校验地址信息失败");
		}
		
		if (ua.getUserId().intValue() != userAddress.getUserId().intValue()) {
			throw new ShopException("客户修改地址到地址表查询校验地址信息失败");
		}
		
		int rows = userAddressMapper.modifyAddress(userAddress);
		
		if (1 != rows) {
			throw new ShopException("客户修改地址到地址表失败");
		}
		
	}

	@Override
	public void deleteAddress(Integer userId, Integer addressId) {
		// TODO Auto-generated method stub
		
		UserAddress userAddress = userAddressMapper.getUserAddress(userId, addressId);
		
		if (null == userAddress) {
			throw new ShopException("客户删除地址到地址表查询校验地址信息失败");
		}
		
		if (addressId.intValue() != userAddress.getId().intValue()) {
			throw new ShopException("客户删除地址到地址表查询校验地址信息失败     非法操作");
		}
		
		if (userId.intValue() != userAddress.getUserId().intValue()) {
			throw new ShopException("客户删除地址到地址表查询校验地址信息失败     非法操作");
		}
		
		int rows = userAddressMapper.deleteAddress(userId, addressId);
		
		if (1 != rows) {
			throw new ShopException("客户删除地址到地址表失败");
		}
		
	}

	
		
	
	
	

}
