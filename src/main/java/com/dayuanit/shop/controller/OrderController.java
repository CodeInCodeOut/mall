package com.dayuanit.shop.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.dayuanit.pay.dto.PayOrderUrlDTO;
import com.dayuanit.shop.domain.Order;
import com.dayuanit.shop.domain.OrderDetail;
import com.dayuanit.shop.dto.AjaxResultDTO;
import com.dayuanit.shop.dto.BuyGoodsDto;
import com.dayuanit.shop.dto.OrderDTO;
import com.dayuanit.shop.dto.OrderDetailDTO;
import com.dayuanit.shop.dto.OrderGoodsDTO;
import com.dayuanit.shop.exception.ShopException;
import com.dayuanit.shop.service.DisplayService;
import com.dayuanit.shop.service.OrderDetailService;
import com.dayuanit.shop.service.OrderService;
import com.dayuanit.shop.utils.PageUtils;
import com.dayuanit.shop.vo.BuyGoodsVo;

@Controller
@RequestMapping("/userOrder")
public class OrderController {
	
	private static final  Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService  orderDetailService;
	
	@RequestMapping("/myorder")
	public String myorder() {
		return "myorder";
	}
	
	@RequestMapping("/toMyOrderDetail")
	public ModelAndView toMyOrderDetail(String orderId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("orderId", orderId);
		mv.setViewName("orderDetail");
		return mv;
	}
	
	@RequestMapping("/toAddress")
	public String toAddress() {
		return "address";
	}
	
	@RequestMapping("/toPay")
	public String toPay() {
		return "pay";
	}
	
	@RequestMapping("/toOrder")
	public ModelAndView toOrder(String orderId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("orderId", orderId);
		mv.setViewName("order");
		return mv;
	}
	
	@RequestMapping("/pay")
	@ResponseBody
	public AjaxResultDTO orderToPay(Integer orderId, Integer payChannel, Integer addressId) {
		
			Integer userId = 1;
			log.info("订单修改状态待支付信息orderId{}", orderId, "订单修改状态待支付信息userId{}", userId, "订单修改状态待支付信息payChannel{}", payChannel, "订单修改状态待支付信息addressId{}", addressId);
			try {
				PayOrderUrlDTO payOrderUrlDTO = orderService.orderToPay(orderId, userId, payChannel, addressId);
			return AjaxResultDTO.success(payOrderUrlDTO);
		} catch (ShopException se) {
			log.error("订单修改状态待支付 异常信息 ， {}", se.getMessage(), se);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		} catch (Exception e) {
			log.error("订单修改状态待支付 异常信息 ， {}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
	}
	
	

	@RequestMapping("/createOrder4JsonBody")
	@ResponseBody
	public AjaxResultDTO createOrder4JsonBody(@RequestBody List<BuyGoodsVo> vos) {
		log.info("购买结算信息List<BuyGoodsVo>{}", vos);
		try {
			
			if (0 ==vos.size()) {
				return AjaxResultDTO.failed("系统异常  请联系客服");
			}
			
			for (BuyGoodsVo vo : vos) {
				log.info("购买结算信息vo.goodsId{}", vo.getGoodsId());
				log.info("购买结算信息vo.goodsAccount{}", vo.getGoodsAccount());
			}
			
			Integer userId = 1;
			Order order = orderService.createOrder(vos, userId);
			
			return AjaxResultDTO.success(order);
		} catch(ShopException se) {
			log.error("订单增加商品 异常信息 ， {}", se.getMessage(), se);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		} catch(Exception e) {
			log.error("订单增加商品 异常信息 ， {}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
	}
	
	
	@RequestMapping("/createOrder4Json")
	@ResponseBody
	public AjaxResultDTO createOrder4Json(String buyMsg) {
		log.info("购买结算信息buyMsg{}", buyMsg);
		try {
			List<BuyGoodsVo> voList = JSON.parseArray(buyMsg, BuyGoodsVo.class);
			for (BuyGoodsVo vo : voList) {
				log.info("购买结算信息vo.goodsId{}", vo.getGoodsId());
				log.info("购买结算信息vo.goodsAccount{}", vo.getGoodsAccount());
			}
			return AjaxResultDTO.success();
		} catch(Exception e) {
			log.error("订单增加商品 异常信息 ， {}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
		
		
	}
	
	@RequestMapping("/createOrder")
	@ResponseBody
	public AjaxResultDTO createOrder(String buyMsg) {
		log.info("购买结算信息buyMsg{}", buyMsg);
		Integer userId = 1;
		
		try {
			
			if (StringUtils.isBlank(buyMsg)) {
				return AjaxResultDTO.failed("信息异常  请联系客服");
			}
			
			List<BuyGoodsDto> bgt = orderService.createOrderFormCart(buyMsg, userId);
			
			return AjaxResultDTO.success(bgt);
			
		} catch(ShopException se) {
			log.error("buyMsg对象{}", buyMsg, "下单异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("下单异常 请联系客服");
		} catch (Exception e) {
			log.error("购物车增加商品异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
	}
	
	
	@RequestMapping("/listOrder")
	@ResponseBody
	public AjaxResultDTO listOrder(Integer orderId) {
		Integer userId = 1;
		try {
			OrderGoodsDTO ogd = orderService.listOrder(orderId, userId);
			return AjaxResultDTO.success(ogd);
		} catch(ShopException se) {
			log.error("下单异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("订单加载异常 请联系客服");
		} catch (Exception e) {
			log.error("下单未知异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
	}
	
	@RequestMapping("/cancelOrder")
	@ResponseBody
	public AjaxResultDTO  cancelOrder(Integer orderId) {
		Integer userId = 1;
		Integer status = 4;
		try {
			orderService.changeOrderStatus(userId, orderId, status);
			return AjaxResultDTO.success();
		} catch(ShopException se) {
			log.error("取消订单异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("订单取消异常 请联系客服");
		} catch (Exception e) {
			log.error("取消订单未知异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
	}
	
	@RequestMapping("/loadMyOrder")
	@ResponseBody
	public AjaxResultDTO loadMyOrder(@RequestParam(required=false, defaultValue="1") Integer pageNum, Integer status, Integer userId) {
		
		userId = 1;
		
		try {
			PageUtils<OrderDTO> pageUtils = orderService.listMyOrder2(userId, status, pageNum);
			return AjaxResultDTO.success(pageUtils);
		} catch(ShopException se) {
			log.error("订单加载异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("订单加载异常 请联系客服");
		} catch (Exception e) {
			log.error("加载订单未知异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
	}
	
	
	@RequestMapping("/listOrderDetail")
	@ResponseBody
	public AjaxResultDTO listOrderDetail(Integer userId, Integer orderId) {
		userId = 1;
		try {
			OrderDetailDTO  orderDetailDTO  = orderDetailService.listOrderDetail(userId, orderId);
			return AjaxResultDTO.success(orderDetailDTO);
		} catch(ShopException se) {
			log.error("加载订单详情异常信息{}", se.getMessage(), se);
			return AjaxResultDTO.failed("加载订单详情异常 请联系客服");
		} catch (Exception e) {
			log.error("加载订单详情未知异常信息{}", e.getMessage(), e);
			return AjaxResultDTO.failed("系统异常  请联系客服");
		}
		
		
		
		
		
	}
	
	
}
