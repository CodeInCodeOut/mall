package com.dayuanit.shop.serviceImpl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.shop.domain.User;
import com.dayuanit.shop.exception.ShopException;
import com.dayuanit.shop.mapper.UserMapper;
import com.dayuanit.shop.service.UserService;


@Service
public  class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(String userName, String password) {
		// TODO Auto-generated method stub
		
		User exitUser = userMapper.queryUserByUserName(userName);
		
		if (null == exitUser) {
			throw new ShopException("用户名或者密码不正确");
		}
		
		// todo 登录密码 加密 校验
		password = DigestUtils.md5Hex(password + userName);
		
		if (!exitUser.getPassword().equals(password)) {
			throw new ShopException("用户名或者密码不正确");
		}
		
		
		return exitUser;
		
	}


	@Override
	public void regist(String userName, String password, String copyPassword, String email) {
		// TODO Auto-generated method stub
		User exitUser = userMapper.queryUserByUserName(userName);
		
		if (null != exitUser) {
			throw new ShopException("用户名被注册");
		}
		
		
		if (!password.equals(copyPassword)) {
			throw new ShopException("注册密码与校验密码不相同 注册失败");
		}
		
		// todo 注册密码 加密
		password = DigestUtils.md5Hex(password + userName);
		
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setEmail(email);
		int rows = userMapper.addUser(user);
		
		if (1 != rows) {
			throw new ShopException("注册失败");
		}
		
	}
	
	

}
