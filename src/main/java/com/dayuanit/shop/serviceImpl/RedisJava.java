package com.dayuanit.shop.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

public class RedisJava {
	
	private static final  Logger log = LoggerFactory.getLogger(RedisJava.class);
	
	public static void main(String[] args) {
		
		
		 Jedis jedis = new Jedis("192.168.8.170", 6379);
		 
		 jedis.lpush("site-list", "Runoob");
		 jedis.lpush("site-list", "Google");
		 jedis.lpush("site-list", "Taobao");
	        // 获取存储的数据并输出
	     List<String> list = jedis.lrange("site-list", 0 ,2);
	        
	     for(int i=0; i<list.size(); i++) {
	       	
	    	 log.info( list.get(i));
	      
	     }
	     
//	     Set<String> keys = jedis.keys("*"); 
//	        Iterator<String> it=keys.iterator() ;   
//	        while(it.hasNext()){   
//	            String key = it.next(); 
//	            log.info(key);
//	        }
//		
	}
	
	

}
