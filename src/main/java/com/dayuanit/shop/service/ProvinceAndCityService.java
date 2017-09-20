package com.dayuanit.shop.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProvinceAndCityService {

	List<Map<String, String>>listProvince();
	
	List<Map<String, String>>listCity(String provincecode);
	
	List<Map<String, String>>listArea(String citycode);
	
	String getProvince(String code);
	
	String getCity(String code);
	
	String getArea(String code);
	
}
