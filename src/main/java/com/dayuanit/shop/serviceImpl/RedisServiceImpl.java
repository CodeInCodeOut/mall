package com.dayuanit.shop.serviceImpl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dayuanit.shop.redis.convert.RedisUtil;
import com.dayuanit.shop.service.RedisService;

import redis.clients.jedis.Jedis;

@Service("redisServiceImpl")
public class RedisServiceImpl implements RedisService{

	private static final  Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);
	
	@Override
	public boolean hasKey(String key) {
		Jedis jedis = null;
		try {
			
			jedis = RedisUtil.getRides();
			return jedis.exists(key);
			
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		
	}

	@Override
	public void setListPCA(String key, List<Map<String, String>> value) {
		Jedis jedis = null;
		try {
			
			jedis = RedisUtil.getRides();
			String valueStr = JSON.toJSONString(value);
			log.info("保存内容， {}", valueStr);
			jedis.set(key, valueStr);
			
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		
	}

	@Override
	public List<Map<String, String>> getPCA(String key) {
		Jedis jedis = null;
		try {
			
			jedis = RedisUtil.getRides();
			List list = JSON.parseArray(jedis.get(key), Map.class);
			
			return list;
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		
	}

	@Override
	public String popPayMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
