package com.dayuanit.shop.redis.convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisUtil {

	private static final  Logger log = LoggerFactory.getLogger(RedisUtil.class);
	
	private static final JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.8.170");
	
	public static Jedis getRides() {
		
		return pool.getResource();
		
	}
	
	public void setValue(String key, String value) {
	
		Jedis jedis = null;
		try {
			jedis = getRides();
			log.info(key, value);
			jedis.set(key, value);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		
	}
	
	public String getValue(String key) {
		Jedis jedis = null;
		String value = "";
		try {
			jedis = getRides();
			value = jedis.get(key);
			log.info(key, value);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
		return value;
	}
	
}
