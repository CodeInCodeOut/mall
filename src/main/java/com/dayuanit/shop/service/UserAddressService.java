package com.dayuanit.shop.service;

import java.util.List;

import com.dayuanit.shop.domain.UserAddress;
import com.dayuanit.shop.vo.UserAddressVo;

public interface UserAddressService {
	
	void addAddress(UserAddressVo userAddressVo);
	
	List<UserAddress> listUserAddressByUserId(Integer userId);
	
	void modifyAddress(UserAddress userAddress);
	
	void deleteAddress(Integer userId, Integer addressId);;

}
