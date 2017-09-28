package com.dayuanit.shop.service;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface RedisService {
	
	boolean hasKey(String key);
	
	void setListPCA(String key, List<Map<String, String>> value);
	
	List<Map<String, String>> getPCA(String key);
	
	String popPayMsg();
	
	void saveCartId(List<Integer> cartIds, Integer userId);
	
	Set<Integer> getCartId(Integer userId);
	
	void deleteKey(String key);
	
	
}
