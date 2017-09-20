package com.dayuanit.shop.Enum;

import com.dayuanit.shop.exception.ShopException;

public enum OrderFromEnum {
	
	FROMGOOGSPAGE(1, "商品详情页"), FROMCART(2, "购物车结算");
	
	private Integer code;
	
	private String value;

	public Integer getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private OrderFromEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public static OrderFromEnum getEnum(Integer code) {
		for (OrderFromEnum ofe : OrderFromEnum.values()) {
			if (code == ofe.getCode()) {
				return ofe;
			}
		}
		throw new ShopException("订单来源--未找到");
	}

}
