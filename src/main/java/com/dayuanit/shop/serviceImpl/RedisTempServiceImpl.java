package com.dayuanit.shop.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.dayuanit.shop.service.OrderService;
import com.dayuanit.shop.service.RedisService;

@Service("redisTempServiceImpl")
public class RedisTempServiceImpl implements RedisService, InitializingBean{
	
	private static final Logger log = LoggerFactory.getLogger(RedisTempServiceImpl.class);
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String, List<Map<String, String>>> PCARedisOpetion;
	
	@Resource(name="redisTemplate")
	private ListOperations<String, String> orderQueue;

	@Autowired
	private OrderService orderService;
	
	@Resource(name="redisTemplate")
	private SetOperations<String, Integer> cartSetOperation;
	
	

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

	@Override
	public String popPayMsg() {
		String key = "dayuanit:pay:order";
		String orderInfo = orderQueue.rightPop(key);
		if (StringUtils.isBlank(orderInfo)) {
			try {
				log.info(">>>>>队列无值，我要睡觉");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			return null; // 没有信息 跳出
		}
		
		try {
			log.info(">>>>>订单支付数据{}", orderInfo);
			String orderMsg[] = orderInfo.split("\\$");
			String orderId = orderMsg[0];
			String payId = orderMsg[1];
			
			orderService.processPayresult(Integer.parseInt(orderId), Integer.parseInt(payId));
		} catch (Throwable t) {
			orderQueue.leftPush(key, orderInfo); // 再放回去
			log.error("处理支付系统与订单系统的交互信息 订单状态改编成已付款状态异常", t);
		}
		
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info(">>>>>afterPropertiesSet-------");
		
			new Thread(new Runnable() {

				@Override
				public void run() {
					while(true) {
						popPayMsg();
					}
					
				}
				
			}, "处理订单状态为支付").start();
		
	}

	@Override
	public void saveCartId(List<Integer> cartIds, Integer userId) {
		String key = "cart:cache:" + userId;
		
		redisTemplate.execute(new SessionCallback() {

			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				Long num = operations.opsForSet().add(key, cartIds.toArray());
				operations.expire(key, 30, TimeUnit.MINUTES);
				log.info("--------cartId cache--------");
				return num;
			}
		});
	}

	@Override
	public Set<Integer> getCartId(Integer userId) {
		String key = "cart:cache:" + userId;
		
		return cartSetOperation.members(key);
	}

	@Override
	public void deleteKey(String key) {
		redisTemplate.delete(key);
		
	}

}
