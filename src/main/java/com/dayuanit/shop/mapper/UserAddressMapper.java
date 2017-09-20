package com.dayuanit.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.shop.domain.UserAddress;

public interface UserAddressMapper {
			
	int addUserAddress(UserAddress userAddress);
	
	List<UserAddress> listUserAddressByUserId(Integer userId);
	
	UserAddress queryUserDefuAddressByUserId(Integer userId);
	
	int deleteAddress(@Param("userId")Integer userId, @Param("addressId")Integer addressId);
	
	int modifyAddress(UserAddress userAddress);
	
	UserAddress getUserAddress(@Param("userId")Integer userId, @Param("addressId")Integer addressId);
}
