package com.dayuanit.shop.dto;

import java.util.List;


public class OrderDetailDTO {
	
	private Integer id;
	
	private Integer status;
	
	private String userRealName;
	
	private String createTime;
	
	private Integer payChannel;
	
	private String totalPrice;
	
	private String dtaAddress;
	
	private String provinceName;
	
	private String cityName;
	
	private String disbursements;
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public String getUserRealName() {
		return userRealName;
	}



	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}



	public String getCreateTime() {
		return createTime;
	}



	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



	public Integer getPayChannel() {
		return payChannel;
	}



	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
	}



	public String getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}



	public String getDtaAddress() {
		return dtaAddress;
	}



	public void setDtaAddress(String dtaAddress) {
		this.dtaAddress = dtaAddress;
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



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getDeliveryTime() {
		return deliveryTime;
	}



	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}



	public String getFreight() {
		return freight;
	}



	public void setFreight(String freight) {
		this.freight = freight;
	}



	public List<OrderGoods> getOrderGoods() {
		return orderGoods;
	}



	public void setOrderGoods(List<OrderGoods> orderGoods) {
		this.orderGoods = orderGoods;
	}



	public String getDisbursements() {
		return disbursements;
	}



	public void setDisbursements(String disbursements) {
		this.disbursements = disbursements;
	}



	private String areaName;
	
	private String phone;
	
	private String deliveryTime;
	
	private String freight;
	
	private List<OrderGoods> orderGoods;
	
	
	
	public static class OrderGoods {
		
		private String goodsName;
		
		private String price;
		
		private Integer goodsAccount;

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public Integer getGoodsAccount() {
			return goodsAccount;
		}

		public void setGoodsAccount(Integer goodsAccount) {
			this.goodsAccount = goodsAccount;
		}
		

	}
}
