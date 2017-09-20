package com.dayuanit.shop.dto;

import com.dayuanit.shop.domain.Goods;

public class BuyGoodsDto {
	
	private Goods goods;
	
	private Integer goodsAccount;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getGoodsAccount() {
		return goodsAccount;
	}

	public void setGoodsAccount(Integer goodsAccount) {
		this.goodsAccount = goodsAccount;
	}
	
	

}
