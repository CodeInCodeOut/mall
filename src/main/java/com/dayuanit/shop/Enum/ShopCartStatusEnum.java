package com.dayuanit.shop.Enum;

import com.dayuanit.shop.exception.ShopException;

public enum ShopCartStatusEnum {
	
	USEABLE(1, "可用"), UNUSEABLE(2, "不可用"),
	GOODSOFF(3, "商品下架"), QUITSHOPCART(4, "取消");
	
	private Integer code;
	
	private String value;
	
	public String getValue() {
		return value;
	}
	
	public Integer getCode() {
		return code;
	}

	private ShopCartStatusEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public ShopCartStatusEnum getEnum(Integer code) {
		for (ShopCartStatusEnum sse : ShopCartStatusEnum.values()) {
			if (code == sse.getCode()) {
				return sse;
			}
		}
		throw new ShopException("---购物车状态---没有合适的类型");
	}

	
	
	


}
