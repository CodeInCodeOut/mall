package com.dayuanit.shop.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class UserVo {
	
	
	@NotNull
	@Length(min=3, max=18, message="用户名长度范围为大于3小于18")
	private String userName;
	
	@NotNull
	@Length(min=1, max=18, message="密码长度范围为大于1小于18")
	private String password;
	
	@NotNull
	@Length(min=1, max=18, message="密码长度范围为大于1小于18")
	private String copyPassword;
	
	
	@NotNull
	@Email(message="邮箱格式不正确")
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCopyPassword() {
		return copyPassword;
	}

	public void setCopyPassword(String copyPassword) {
		this.copyPassword = copyPassword;
	}
	
	

}
