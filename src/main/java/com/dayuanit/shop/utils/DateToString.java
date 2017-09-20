package com.dayuanit.shop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString {
	
	public static String to(Date date) {
		
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
		
	}

}
