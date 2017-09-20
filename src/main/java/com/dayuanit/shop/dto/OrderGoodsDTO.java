package com.dayuanit.shop.dto;

import java.util.List;

public class OrderGoodsDTO {
	
	private String totalPrice;
	
	private String freight;
	
	private String preferentialMoney;
	
	private List<GoodsInfo> goods;
	
	
	
	
	
	public String getTotalPrice() {
		return totalPrice;
	}





	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}





	public String getFreight() {
		return freight;
	}





	public void setFreight(String freight) {
		this.freight = freight;
	}





	public String getPreferentialMoney() {
		return preferentialMoney;
	}





	public void setPreferentialMoney(String preferentialMoney) {
		this.preferentialMoney = preferentialMoney;
	}





	public List<GoodsInfo> getGoods() {
		return goods;
	}





	public void setGoods(List<GoodsInfo> goods) {
		this.goods = goods;
	}





	public static class GoodsInfo {
		
		private String goodsName;
		
		private String thisTotalPrice;
		
		private Integer goodsAccount;

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public String getThisTotalPrice() {
			return thisTotalPrice;
		}

		public void setThisTotalPrice(String thisTotalPrice) {
			this.thisTotalPrice = thisTotalPrice;
		}

		public Integer getGoodsAccount() {
			return goodsAccount;
		}

		public void setGoodsAccount(Integer goodsAccount) {
			this.goodsAccount = goodsAccount;
		}
		
		
		
	}

}
