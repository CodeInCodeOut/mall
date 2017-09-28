package com.dayuanit.shop.dto;

import java.util.List;

public class OrderDTO {
	
	private Integer id;
	
	private String status;
	
	private String userRealName;
	
	private String createTime;
	
	private Integer payChannel;
	
	private String totalPrice;
	
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
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	

	public List<OrderGoods> getOrderGoods() {
		return orderGoods;
	}

	public void setOrderGoods(List<OrderGoods> orderGoods) {
		this.orderGoods = orderGoods;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
