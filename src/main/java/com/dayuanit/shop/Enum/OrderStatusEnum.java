package com.dayuanit.shop.Enum;

import com.dayuanit.shop.exception.ShopException;

public enum OrderStatusEnum {
	
	GOSETTLEMENT(0, "去结算"), UNPAY(1, "代付款"), 
	PAID(2, "已付款"), ORDERFAILURE(3, "订单失效"), 
	QUITORDER(4,"取消订单");
	
	private Integer code;
	
	private String value;
	
	public String getValue() {
		return value;
	}
	

	private OrderStatusEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}

	public Integer getCode() {
		return code;
	}

	
	public static OrderStatusEnum getEnum(Integer code) {
		for (OrderStatusEnum ose : OrderStatusEnum.values()) {
			if (code == ose.getCode()) {
				return ose;
			}
		}
		throw new ShopException("订单状态---没有合适的类型");
	}

	
	

}
