package com.dayuanit.shop.domain;

import java.util.Date;

public class UserAddress {
	
	private Integer id;
	
	private Integer userId;
	
	private String provinceCode;
	
	private String cityCode;
	
	private String areaCode;
	
	private String dtaAddress;
	
	private String userRealName;
	
	private String phone;
	
	private Integer status;
	
	private Integer isDefauAdress;
	
	private Date  createTime;
	
	private Date modifyTime;
	
	private String provinceName;
	
	private String cityName;
	
	private String areaName;
	
	private String payChannel;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	
	
	
	
	
	
	
	

}
