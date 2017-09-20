package com.dayuanit.shop.Enum;

import com.dayuanit.shop.exception.ShopException;

public enum PayTypeEnum {
	
	CASH_ON_DELIVERY(1, "货到付款"), ALIPAY(2, "支付宝"), WEIXINPAY(3, "微信支付"),
	JDPAY(4, "新浪支付"), YEEPAY(5, "易宝支付"), BANK_DIRECT(6, "银行直连"), OFFLINE_REMITTANCE(7, "线下付款");

	private int type;
	private String desc;
	
	private PayTypeEnum (int type, String desc) {
		this.type = type;
		this.desc = desc;
	}
	
	public static PayTypeEnum getPayType(int type) {
		for (PayTypeEnum pe : PayTypeEnum.values()) {
			if (type == pe.getType()) {
				return pe;
			}
		}
		
		throw new ShopException("没有对应的支付方式");
	}

	public int getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}
	
}
