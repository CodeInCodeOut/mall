package com.dayuanit.shop.service;

import java.util.List;
import java.util.Map;


public interface RedisService {
	
	boolean hasKey(String key);
	
	void setListPCA(String key, List<Map<String, String>> value);
	
	List<Map<String, String>> getPCA(String key);
	
}
