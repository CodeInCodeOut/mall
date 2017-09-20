package com.dayuanit.shop.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.dayuanit.shop.service.RedisService;

@Service("redisTempServiceImpl")
public class RedisTempServiceImpl implements RedisService{
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String, List<Map<String, String>>> PCARedisOpetion;

	@Override
	public boolean hasKey(String key) {
		
		return redisTemplate.hasKey(key);
	}

	@Override
	public void setListPCA(String key, List<Map<String, String>> value) {
		// TODO Auto-generated method stub
		
		PCARedisOpetion.set(key, value);
		
	}

	@Override
	public List<Map<String, String>> getPCA(String key) {
		return PCARedisOpetion.get(key);
	}

}
