package com.dayuanit.shop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProvinceAndCityMapper {
	
	List<Map<String, String>>listProvince();
	
	List<Map<String, String>>listCity(String provincecode);
	
	List<Map<String, String>>listArea(String citycode);
	
	String getProvince(@Param("code") String code);
	
	String getCity(@Param("code") String code);
	
	String getArea(@Param("code") String code);
	
}
