package com.dayuanit.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dayuanit.shop.domain.User;
import com.dayuanit.shop.exception.ShopException;


public class BaseController {
	
	protected static final String LOGIN_FLAG = "loginUser";
	
	protected User getUser(HttpServletRequest req) {
	
	Object objectUser = req.getSession().getAttribute(LOGIN_FLAG);
	if (null == objectUser) {
		throw new ShopException("用户未登录 ");
	}
	
	return (User)objectUser;
	
	}
	
	protected Integer getUserId(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Object objectUser = session.getAttribute(LOGIN_FLAG);
		
		if (null == objectUser) {
			throw new ShopException("登录用户为空");
		}
		
		return ((User) objectUser).getId();	
	}
	
	protected String getUsername(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Object objectUser = session.getAttribute(LOGIN_FLAG);
		
		if (null == objectUser) {
			throw new ShopException("登录用户为空");
		}
		
		return ((User) objectUser).getUserName();
	}
	
	protected String getUserEmail(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Object objectUser = session.getAttribute(LOGIN_FLAG);
		
		if (null == objectUser) {
			throw new ShopException("登录用户为空");
		}
		
		return ((User)objectUser).getEmail();
	}
	
}
