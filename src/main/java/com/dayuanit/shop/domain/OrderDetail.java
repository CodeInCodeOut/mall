package com.dayuanit.shop.domain;

import java.util.Date;

public class OrderDetail {
	
	private Integer id;
	
	private Integer orderId;
	
	private Integer goodsId;
	
	private String price;
	
	private Integer goodsAccount;
	
	private String goodsTotalPrice;
	
	private String goodsName;
	
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
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

	public String getGoodsTotalPrice() {
		return goodsTotalPrice;
	}

	public void setGoodsTotalPrice(String goodsTotalPrice) {
		this.goodsTotalPrice = goodsTotalPrice;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	
	
}
