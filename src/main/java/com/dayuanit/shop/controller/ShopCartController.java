package com.dayuanit.shop.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.shop.domain.Goods;
import com.dayuanit.shop.domain.ShopCart;
import com.dayuanit.shop.dto.AjaxResultDTO;
import com.dayuanit.shop.exception.ShopException;
import com.dayuanit.shop.service.DisplayService;
import com.dayuanit.shop.service.ShopCartService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/shopCart")
public class ShopCartController extends BaseController{
	
	private static final  Logger log = LoggerFactory.getLogger(ShopCartController.class);
	
	@Autowired
	private ShopCartService shopCartService;
	
	@Autowired
	private DisplayService displayService;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@RequestMapping("/toIncreaseGoods")
	@ResponseBody
	public AjaxResultDTO toIncreaseGoods(HttpServletRequest req, Integer goodsId, Integer goodsAccount,  ModelMap mm) {
		int userID = 1;
		String cartKey = "%s:%s";
		String key = String.format(cartKey, String.valueOf(userID), String.valueOf(goodsId));
		try {
			
			boolean setFlag = redisTemplate.execute(new RedisCallback<Boolean>() {

				@Override
				public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
					
					return connection.setNX(key.getBytes(), "uniqueCartToken".getBytes());
				}
			});
			
			if (!setFlag) {
				return AjaxResultDTO.failed("重复提交 请稍后再试");
			}
			
			Goods thisGoods = displayService.getGoodsById(goodsId);
			mm.addAttribute("thisGoods", thisGoods);
			
			log.info("userID{}",userID, "goodsId{}", goodsId, "goodsAccount{}",goodsAccount);
			shopCartService.increaseGoodsToShopCart(userID, goodsId, goodsAccount);
			return AjaxResultDTO.success();
			
		} catch (ShopException se) {
			log.error("商品详情页 增加 减少 购物车的商品数量异常  商品ID{}", goodsId, "商品数量goodsAccount{}", goodsAccount);
			log.error("购物车增加商品异常信息{}", se.getMessage(), se);
			
			return AjaxResultDTO.failed("系统异常  请联系客服");
			
		} catch (Exception e) {
			
			log.error("商品详情页 增加 减少 购物车的商品数量异常  商品ID{}", goodsId, "商品数量goodsAccount{}", goodsAccount);
			log.error("购物车增加商品异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		} finally {
			redisTemplate.opsForValue().set(key, "uniqueCartToken", 10, TimeUnit.SECONDS);
		}
		 
	}
	
	@RequestMapping("/toCart")  
	public String toCart(){
		
		return "cart";
	}
	
	@RequestMapping("/cart")
	@ResponseBody
	public AjaxResultDTO cart(HttpServletRequest req) {
		try {
			int userID = 1;
			List<ShopCart> userShopCart = shopCartService.getShopCart(userID);
			ObjectMapper om = new ObjectMapper();
			String json = om.writerWithDefaultPrettyPrinter().writeValueAsString(AjaxResultDTO.success(userShopCart));
			log.info(json);
			return AjaxResultDTO.success(userShopCart);
		} catch (Exception e) {
			log.error("异常信息 ", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常 请联系客服");
		}
	}
	
	
	@RequestMapping("/getGoodsInShopCart")
	@ResponseBody
	public AjaxResultDTO getGoodsInShopCart(Integer goodsId, ModelMap mm, Integer goodsAccount) {
		
		try {
			log.error("goodsId{}",goodsId);
			Goods thisGoods = displayService.getGoodsById(goodsId);
			
			mm.addAttribute("goodsAccount", goodsAccount);
			
			ObjectMapper om = new ObjectMapper();
			String json = om.writerWithDefaultPrettyPrinter().writeValueAsString(AjaxResultDTO.success(thisGoods));
			log.error(json);
			
			return AjaxResultDTO.success(thisGoods);
		} catch(ShopException se) {
			log.error("购物车展示商品异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		} catch(Exception e) {
			log.error("异常信息 ", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常 请联系客服");
		}
		
	}
	
	@RequestMapping("/deleteGoodsShopCartId")
	@ResponseBody
	public AjaxResultDTO deleteGoodsShopCartId(Integer shopCartId) {
		
		try {
			Integer userId = 1;
			shopCartService.deleteGoodsById(userId, shopCartId);
			return AjaxResultDTO.success();
		} catch (ShopException se) {
			log.error("删除购物车商品异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		} catch (Exception e) {
			log.error("异常信息 ", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常 请联系客服");
		}
		
	}
	
	
	
	

}
