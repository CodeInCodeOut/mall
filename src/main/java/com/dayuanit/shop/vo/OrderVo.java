package com.dayuanit.shop.vo;

public class OrderVo {
	
	private Integer userId;
	
	private Integer goodsId;
	
	private Integer goodsAccount;
	
	private Integer cartId;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getGoodsAccount() {
		return goodsAccount;
	}

	public void setGoodsAccount(Integer goodsAccount) {
		this.goodsAccount = goodsAccount;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

}
