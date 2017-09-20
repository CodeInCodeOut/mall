package com.dayuanit.shop.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class UserAddressVo {
	
	private Integer id;
	
	private Integer userId;
	
	@NotNull
	@Length(min=6, max=6, message="provinceCode长度为6")
	private String provinceCode;
	
	
	@NotNull
	@Length(min=6, max=6, message="cityCode长度为6")
	private String cityCode;
	
	
	@NotNull
	@Length(min=6, max=6, message="areaCode长度为6")
	private String areaCode;
	
	@NotNull
	private String dtaAddress;
	
	@NotNull
	private String userRealName;
	
	@NotNull
	private String phone;
	
	private Integer status;
	
	@NotNull
	private Integer isDefauAdress;
	
	@NotNull
	private String provinceName;
	
	@NotNull
	private String cityName;
	
	@NotNull
	private String areaName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getDtaAddress() {
		return dtaAddress;
	}

	public void setDtaAddress(String dtaAddress) {
		this.dtaAddress = dtaAddress;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDefauAdress() {
		return isDefauAdress;
	}

	public void setIsDefauAdress(Integer isDefauAdress) {
		this.isDefauAdress = isDefauAdress;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	

}
