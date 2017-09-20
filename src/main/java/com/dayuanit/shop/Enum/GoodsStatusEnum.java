package com.dayuanit.shop.Enum;

import com.dayuanit.shop.exception.ShopException;

public enum GoodsStatusEnum {
	
	
	GOODSUP(1, "商品上架"), GOODSDOWN(2, "商品下架"); 
	
	private Integer code;
	
	private String value;

	public Integer getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private GoodsStatusEnum(Integer code, String value) {
		this.code = code;
		this.value = value;
	}
	
	public static GoodsStatusEnum getEnum(Integer code) {
		for (GoodsStatusEnum ofe : GoodsStatusEnum.values()) {
			if (code == ofe.getCode()) {
				return ofe;
			}
		}
		throw new ShopException("商品上架下架状态--未找到");
	}

}
