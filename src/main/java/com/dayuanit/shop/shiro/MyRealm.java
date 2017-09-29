package com.dayuanit.shop.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;

import com.dayuanit.shop.service.UserService;

public class MyRealm extends AuthenticatingRealm {
	
	private static final String REALM_NAME = "my_realm";
	
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public static String getRealmName() {
		return REALM_NAME;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		String userName = upToken.getUsername();
		String password = String.valueOf(upToken.getPassword());
		userService.login(userName, password);
		
		
		return new SimpleAuthenticationInfo(userName, password, getRealmName());
	}

	

	

}
