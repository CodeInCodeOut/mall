package com.dayuanit.shop.utils;

import java.math.BigDecimal;

public class CalculateUtil {
	
	public static String mul(String num1, String num2) {
		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(num2);
	
		
		return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
	}
	
	
	public static String plus(String num1, String num2) {
		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(num2);
		
		return b1.add(b2).toString();
	}
	
	

}
