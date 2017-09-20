package com.dayuanit.shop.service;

import com.dayuanit.shop.domain.User;

public interface UserService {
	
	void regist(String userName, String password, String copyPassword, String email);
	
	User login(String userName, String password);

}
