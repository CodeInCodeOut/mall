package com.dayuanit.shop.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.shop.domain.Goods;
import com.dayuanit.shop.domain.GoodsSort;
import com.dayuanit.shop.dto.AjaxResultDTO;
import com.dayuanit.shop.exception.ShopException;
import com.dayuanit.shop.service.DisplayService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/display")
public class DisplayController {
	
	
	private static final  Logger log = LoggerFactory.getLogger(DisplayController.class);
	
	@Autowired
	private DisplayService displayService;
	
//	@RequestMapping("/displayView")
//	@ResponseBody
//	public AjaxResultDTO displayView(HttpServletRequest req, Integer sortId) {
//		try {
//			log.info("sortId{}", sortId);
//			Map<String, Object> map = displayService.display(sortId);
//			return AjaxResultDTO.success(map);
//		} catch(Exception e) {
//			log.error("查询商品异常信息{}", sortId);
//			return AjaxResultDTO.failed("访问异常");
//		}
//	
//	}
	
	@RequestMapping("/ListSort")
	@ResponseBody
	public AjaxResultDTO listSort() {
		try {
			List<GoodsSort> listSort = displayService.listSort();
			log.info("listSort商品类目：{}", listSort);
			ObjectMapper om = new ObjectMapper();
			String json = om.writerWithDefaultPrettyPrinter().writeValueAsString(AjaxResultDTO.success(listSort));
			System.out.println(json);
			return AjaxResultDTO.success(listSort);
		} catch (ShopException se) {
			log.error("首页商品加载 异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("系统异常  请联系客服");
			} catch (Exception e) {
			log.error("listSort商品类目异常：{}", e.getMessage(), e );
			return AjaxResultDTO.failed("访问异常");
		} 
			
	}
	
	@RequestMapping("/listGoods")
	@ResponseBody
	public AjaxResultDTO listGoods(HttpServletRequest req, Integer sortId) {
		try {
			List<Goods> listGoods = displayService.listGoods(sortId);
			log.info("sortId商品类目：{}", sortId);
			log.info("listGoods商品类目下集合：{}", listGoods);
			return AjaxResultDTO.success(listGoods);
		} catch (ShopException se) {
			log.error("首页商品加载 异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("系统异常  请联系客服");
			} catch (Exception e) {
			log.error("商品类目下集合 异常：{}", e.getMessage(), e );
			return AjaxResultDTO.failed("访问异常");
		} 
		
	}
	
	@RequestMapping("/toGoodsIntroduce")
	public String toGoodsIntroduce(Integer goodsId, ModelMap mm) {
		Goods thisGoods = displayService.getGoodsById(goodsId);
		mm.addAttribute("thisGoods", thisGoods);
		return "goods_introduce";
	}
	
	
}
