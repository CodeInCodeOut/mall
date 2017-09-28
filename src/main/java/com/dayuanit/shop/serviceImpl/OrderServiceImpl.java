package com.dayuanit.shop.serviceImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.pay.domain.PayOrder;
import com.dayuanit.pay.dto.PayOrderUrlDTO;
import com.dayuanit.pay.service.PayService;
import com.dayuanit.shop.Enum.GoodsStatusEnum;
import com.dayuanit.shop.Enum.OrderFromEnum;
import com.dayuanit.shop.Enum.OrderStatusEnum;
import com.dayuanit.shop.domain.Goods;
import com.dayuanit.shop.domain.Order;
import com.dayuanit.shop.domain.OrderDetail;
import com.dayuanit.shop.domain.UserAddress;
import com.dayuanit.shop.dto.BuyGoodsDto;
import com.dayuanit.shop.dto.OrderDTO;
import com.dayuanit.shop.dto.OrderDTO.OrderGoods;
import com.dayuanit.shop.dto.OrderGoodsDTO;
import com.dayuanit.shop.dto.OrderGoodsDTO.GoodsInfo;
import com.dayuanit.shop.exception.ShopException;
import com.dayuanit.shop.mapper.GoodsMapper;
import com.dayuanit.shop.mapper.OrderDetailMapper;
import com.dayuanit.shop.mapper.OrderMapper;
import com.dayuanit.shop.mapper.UserAddressMapper;
import com.dayuanit.shop.service.DisplayService;
import com.dayuanit.shop.service.OrderService;
import com.dayuanit.shop.utils.CalculateUtil;
import com.dayuanit.shop.utils.DateToString;
import com.dayuanit.shop.utils.PageUtils;
import com.dayuanit.shop.vo.BuyGoodsVo;
import com.dayuanit.shop.vo.OrderVo;

@Service
public class OrderServiceImpl implements OrderService{
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	@Autowired
	private UserAddressMapper userAddressMapper;
	
	@Autowired
	private PayService payService;

	@Autowired
	private DisplayService displayService;
	

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Order createOrder(List<BuyGoodsVo> vos, Integer userId) {
		// TODO Auto-generated method stub
		
		List<OrderDetail> goodsList = new ArrayList<OrderDetail>(vos.size());
		String totalMoney = "0";
		for(BuyGoodsVo bgv : vos) {
			log.info("购买结算信息vo.goodsId{}", bgv.getGoodsId());
			log.info("购买结算信息vo.goodsAccount{}", bgv.getGoodsAccount());
			
			Goods exitGoods = goodsMapper.getGoodsById(bgv.getGoodsId());
			
			if (null == exitGoods) {
				throw new ShopException("--购物车结算--- 查询商品表  ----- 失败");	
			}
			
			if (GoodsStatusEnum.GOODSDOWN.getCode() == exitGoods.getStatus()) {
				throw new ShopException(String.format("商品%s已下架", exitGoods.getGoodsName()));
			}
			
			if (bgv.getGoodsAccount() > exitGoods.getGoodsRepertory()) {
				throw new ShopException(String.format("商品%s库存不足", exitGoods.getGoodsName()));	
			}
			
			OrderDetail od = new OrderDetail();
			
			String thisTotalMoney = CalculateUtil.mul(exitGoods.getPrice(), String.valueOf(bgv.getGoodsAccount()));
			od.setGoodsAccount(bgv.getGoodsAccount());
			od.setGoodsName(exitGoods.getGoodsName());
			od.setGoodsTotalPrice(thisTotalMoney);
			od.setPrice(exitGoods.getPrice());
			od.setGoodsId(exitGoods.getId());
			
			goodsList.add(od);
			
			totalMoney = CalculateUtil.plus(totalMoney, thisTotalMoney);
		}
		
		Order order = new Order();
		order.setTotalPrice(totalMoney);
		order.setOrderFrom(OrderFromEnum.FROMCART.getCode());
		order.setStatus(OrderStatusEnum.GOSETTLEMENT.getCode());
		order.setUserId(userId);
		int rows = orderMapper.createOrder(order);
		if (1 != rows) {
			throw new ShopException("客户--结算--生成订单表-失败");
		}
		
		for (OrderDetail od : goodsList) {
			od.setOrderId(order.getId());
			rows = orderDetailMapper.saveOrderDetail(od);
			if (1 != rows) {
				throw new ShopException("客户--结算--生成订单详情表-失败");
			}
		}
		
		return order;
	}

	@Override
	public void changeOrderStatus(Integer userId, Integer orderId, Integer status) {
		
		
		orderMapper.changeOrderStatus(orderId, userId, status);
	}

	@Override
	public void deleteOrder(Integer status, Integer userId, Integer orderId) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void rollBackRepertoryTimeOut(Order order) {
		// TODO Auto-generated method stub
		if (order.getStatus() != OrderStatusEnum.UNPAY.getCode()) {
			return;
		}
		
		Order lockOrder = orderMapper.getOrderForUpdate(order.getId());
		if (null == lockOrder) {
			throw new ShopException("超时回滚----更新库存 ---- 锁order表  ----- 失败");
		}
		
		if (lockOrder.getStatus() != OrderStatusEnum.UNPAY.getCode()) {
			return;
		}
		
		Date orderTime = order.getModifyTime();
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderTime);
		cal.add(Calendar.MINUTE, 30);
		
		Date expTime = cal.getTime();
		
		if (expTime.after(new Date())) {
			log.info(">>>>>处理订单{}时间不过期》》》", order.getId());
			return;
		}
		
		List<OrderDetail> listOrderDetail = orderDetailMapper.listOrderDetail(lockOrder.getId());
		for (OrderDetail orderDetail : listOrderDetail) {
			log.info("库存回滚商品信息getGoodsAccount{}", orderDetail.getGoodsAccount(), "getGoodsId{}", orderDetail.getGoodsId());
			
			Goods goods = goodsMapper.getGoodsByIdForUpdate(orderDetail.getGoodsId());
			if (null == goods) {
				throw new ShopException("超时回滚----更新库存 ---- 查询商品表  ----- 失败");
			}
			
			int rows = goodsMapper.changeGoodsRepertory(orderDetail.getGoodsAccount(), orderDetail.getGoodsId());
			
			if (1 != rows) {
				throw new ShopException("超时回滚----更新库存---失败");
			}
			
		}
		
		int rows = orderMapper.changeOrderStatus(lockOrder.getId(), lockOrder.getUserId(), OrderStatusEnum.ORDERFAILURE.getCode());
		
		if (1 != rows) {
			throw new ShopException("超时回滚----更新订单状态为失效---失败");
		}
		
	}

	@Override
	public void createorder(OrderVo orderVo) {
		// TODO Auto-generated method stub
		Order order = new Order();
		
		
	}

	@Override
	public List<BuyGoodsDto> createOrderFormCart(String buyMsg, Integer userId) {
		// TODO Auto-generated method stub
		List<BuyGoodsDto> bgdList = new ArrayList<BuyGoodsDto>();
		
		String [] cartMsgArray = buyMsg.split("\\$");
		for (String msg : cartMsgArray) {
			if (StringUtils.isBlank(msg)) {
				continue;
			}
			
			String [] goodsInfo = msg.split("\\-");
			
			Goods goods = goodsMapper.getGoodsById(Integer.parseInt(goodsInfo[0]));
			
			if (null == goods) {
				throw new ShopException("--购物车结算--- 查询商品表  ----- 失败");	
			}
			
			if (goods.getStatus() == 2) {
				throw new ShopException("--购物车结算--- 查询商品表  ----- 失败---商品下架");	
			}
			
			if (goods.getGoodsRepertory() < Integer.parseInt(goodsInfo[0])) {
				throw new ShopException("--购物车结算--- 查询商品表  ----- 失败---商品库存不足");
			}
			
			BuyGoodsDto buyGoodsDto = new BuyGoodsDto();
			bgdList.add(buyGoodsDto);
			
			buyGoodsDto.setGoods(goods);
			buyGoodsDto.setGoodsAccount(Integer.parseInt(goodsInfo[1]));
			
		}
		
		excuteCreateOrder(bgdList, userId);
		
		return bgdList;
	}
	
	

	
//	public void excuteCreateOrder(List<BuyGoodsDto> bgdList, Integer userId){
//		
//		Order order = new Order();
//		order.setStatus(0);
//		order.setOrderFrom(2);
//		order.setUserId(userId);
//		
//		String totalPrice = "0";
//		for (BuyGoodsDto bgt : bgdList) {
//			String thisTotalPrice = CalculateUtil.mul(Integer.toString(bgt.getGoodsAccount()), Integer.toString(bgt.getGoods().getPrice()));
//			totalPrice = CalculateUtil.plus(thisTotalPrice, totalPrice);
//		}
//		
//		order.setTotalPrice(Integer.parseInt(totalPrice));
//		int rows = orderMapper.createOrder(order);
//		if (1 != rows) {
//			throw new ShopException("客户--结算--生成订单表-失败");
//		}
//		
//		OrderDetail orderDetail = new OrderDetail();
//		
//		for (BuyGoodsDto bgt : bgdList) {
//			orderDetail.setGoodsId(bgt.getGoods().getId());
//			orderDetail.setGoodsAccount(bgt.getGoodsAccount());
//			orderDetail.setPrice(bgt.getGoods().getPrice());
//			
//			String thisTotalPrice = CalculateUtil.mul(Integer.toString(orderDetail.getGoodsAccount()), Integer.toString(orderDetail.getPrice()));
//			orderDetail.setGoodsTotalPrice(Integer.parseInt(thisTotalPrice));
//			
//			orderDetail.setOrderId(order.getId());
//			orderDetail.setGoodsName(bgt.getGoods().getGoodsName());
//			rows = orderDetailMapper.saveOrderDetail(orderDetail);
//			if (1 != rows) {
//				throw new ShopException("客户--结算--生成订单详情表-失败");
//			}
//		}
//		
//	}

	@Override
	public List<BuyGoodsDto> createOrder4JsonBody(List<BuyGoodsVo> vos, Integer userId) {
		// TODO Auto-generated method stub
		
		List<BuyGoodsDto> bdt = new ArrayList<BuyGoodsDto>(vos.size());
		
		for (BuyGoodsVo vo : vos) {
			log.info("购买结算信息vo.goodsId{}", vo.getGoodsId());
			log.info("购买结算信息vo.goodsAccount{}", vo.getGoodsAccount());
			
			Goods goods = goodsMapper.getGoodsById(vo.getGoodsId());
			
			if (null == goods) {
				throw new ShopException("--购物车结算--- 查询商品表  ----- 失败");	
			}
			
			if (goods.getStatus() == 2) {
				throw new ShopException("--购物车结算--- 查询商品表  ----- 失败---商品下架");	
			}
			
			if (goods.getGoodsRepertory() < vo.getGoodsAccount()) {
				throw new ShopException("--购物车结算--- 查询商品表  ----- 失败---商品库存不足");
			}
			
			BuyGoodsDto bd = new BuyGoodsDto();
			bd.setGoods(goods);
			bd.setGoodsAccount(vo.getGoodsAccount());
			
			bdt.add(bd);
		}
		
		excuteCreateOrder(bdt, userId);
		
		return bdt;
	}


	public void excuteCreateOrder(List<BuyGoodsDto> bdt, Integer userId) {
		Order order = new Order();
		order.setStatus(0);
		order.setOrderFrom(OrderFromEnum.FROMCART.getCode());
		order.setUserId(userId);
		
		String totalMoney = "0";
		for (BuyGoodsDto bd : bdt) {
			String goodsMoney = CalculateUtil.mul(bd.getGoods().getPrice(), String.valueOf(bd.getGoodsAccount()));
			CalculateUtil.plus(goodsMoney, totalMoney);
		}
		
		
		order.setTotalPrice(totalMoney);
		int rows = orderMapper.createOrder(order);
		if (1 != rows) {
			throw new ShopException("客户--结算--生成订单表-失败");
		}
		
		for (BuyGoodsDto bd : bdt) {
			String goodsMoney = CalculateUtil.mul(bd.getGoods().getPrice(), Integer.toString(bd.getGoodsAccount()));
			OrderDetail od = new OrderDetail();
			od.setGoodsAccount(bd.getGoodsAccount());
			od.setGoodsId(bd.getGoods().getId());
			od.setGoodsName(bd.getGoods().getGoodsName());
			od.setGoodsTotalPrice(goodsMoney);
			od.setOrderId(order.getId());
			od.setPrice(od.getPrice());
			
			rows = orderDetailMapper.saveOrderDetail(od);
			
			if (1 != rows) {
				throw new ShopException("客户--结算--生成订单详情表-失败");
			}
			
		}
	}

	@Override
	public OrderGoodsDTO listOrder(Integer orderId, Integer userId) {
		// TODO Auto-generated method stub
		userId = 1;
		Order order = orderMapper.getOrderById(orderId, userId);
		
		if (null == order) {
			throw new ShopException("--生成订单--- 查询商品表  ----- 失败");	
		}
		
		List<OrderDetail> listorderDetail = orderDetailMapper.listOrderDetail(order.getId());
		if (null == listorderDetail) {
			throw new ShopException(String.format("订单ID%s订单查询失败, 订单详情表查询失败",order.getId()));	
		}
		
		List<GoodsInfo> listGoodsInfo = new ArrayList<GoodsInfo>(listorderDetail.size());
		
		OrderGoodsDTO ogd = new OrderGoodsDTO();
		GoodsInfo gi = new GoodsInfo();
		for (OrderDetail od : listorderDetail) {
			gi.setGoodsName(od.getGoodsName());
			gi.setGoodsAccount(od.getGoodsAccount());
			gi.setThisTotalPrice(od.getGoodsTotalPrice());
			listGoodsInfo.add(gi);
		}
		ogd.setGoods(listGoodsInfo);
		ogd.setTotalPrice(order.getTotalPrice());
		
		return ogd;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public PayOrderUrlDTO orderToPay(Integer orderId, Integer userId, Integer payChannel, Integer addressId) {
		// TODO Auto-generated method stub
		Order order = orderMapper.getOrderById(orderId, userId);
		
		if (null == order) {
			throw new ShopException("--订单支付--- 查询商品表  ----- 失败");	
		}
		
		if (order.getStatus() != OrderStatusEnum.GOSETTLEMENT.getCode() && order.getStatus() != OrderStatusEnum.UNPAY.getCode()) {
			throw new ShopException("--订单不能支付---");	
		}
		
		Date orderTime = order.getModifyTime();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderTime);
		cal.add(Calendar.MINUTE, 30);
		
		Date expectedTime = cal.getTime();
		if (new Date().after(expectedTime)) {
			throw new ShopException("--订单不能支付---");	
		}
		
		if (order.getStatus() == OrderStatusEnum.GOSETTLEMENT.getCode()) {
			int rows = orderMapper.changeOrderStatus(orderId, userId, OrderStatusEnum.UNPAY.getCode());
			if (1 != rows) {
				throw new ShopException("--订单更新失败---");		
			}
		}
		
		UserAddress userAddress = userAddressMapper.getUserAddress(userId, addressId);
		if (null == userAddress) {
			throw new ShopException("--订单支付--- 查询地址表  ----- 失败");	
		}
		
		order.setAreaName(userAddress.getAreaName());
		order.setCityName(userAddress.getCityName());
		order.setProvinceName(userAddress.getProvinceName());
		order.setDtaAddress(userAddress.getDtaAddress());
		order.setPayChannel(payChannel);
		order.setUserRealName(userAddress.getUserRealName());
		order.setPhone(userAddress.getPhone());

		int rows = orderMapper.updateOrderToPay(order);
		
		if (1 != rows) {
			throw new ShopException("--订单更新待付款失败---");		
		}
		
		
		List<OrderDetail> listOrderDetail = orderDetailMapper.listOrderDetail(order.getId());
		for (OrderDetail orderDetail : listOrderDetail) {
			log.info("下单支付减库存商品getGoodsAccount{}", orderDetail.getGoodsAccount(), "getGoodsId{}", orderDetail.getGoodsId());
			displayService.subGoodsRepertory(orderDetail.getGoodsAccount(), orderDetail.getGoodsId());
		}
		
		//请求支付系统 获取支付地址
		PayOrder payOrder = new PayOrder();
		payOrder.setAmount(order.getTotalPrice());
		payOrder.setBankId(null);
		payOrder.setBizId(String.valueOf(order.getId()));
		payOrder.setDetailMsg("大猿商城");
		payOrder.setPayChannel(order.getPayChannel());
		payOrder.setUserId(order.getUserId());
		
		PayOrderUrlDTO payOrderUrlDTO = payService.addPayOrder(payOrder);
		
		return payOrderUrlDTO;
		
		
	}

	@Override
	public List<OrderDTO> listMyOrder(Integer userId, Integer status) {
		// TODO Auto-generated method stub
		
		if (-1 == status) {
			status = null;
		}
		
		List<Order> listOrder = orderMapper.listOrderByUserIdAndStatus(userId, status);
		List<OrderDTO> listOrderDto = new ArrayList<OrderDTO>(listOrder.size());
		for(Order order : listOrder) {
			OrderDTO orderDTO = new OrderDTO();
			
			orderDTO.setCreateTime(DateToString.to(order.getCreateTime()));
			orderDTO.setId(order.getId());
			orderDTO.setStatus(OrderStatusEnum.getEnum(order.getStatus()).getValue());
			orderDTO.setTotalPrice(order.getTotalPrice());
			orderDTO.setUserRealName(order.getUserRealName());
			
			List<OrderDetail> orderDetailList = orderDetailMapper.listOrderDetail(order.getId());
			List<OrderGoods> listOrderGoods = new ArrayList<OrderGoods>(orderDetailList.size());
			
			for (OrderDetail od : orderDetailList) {
				OrderGoods  orderGoods = new OrderGoods();
				orderGoods.setGoodsAccount(od.getGoodsAccount());
				orderGoods.setGoodsName(od.getGoodsName());
				orderGoods.setPrice(od.getPrice());
				
				listOrderGoods.add(orderGoods);
			}
			
			orderDTO.setOrderGoods(listOrderGoods);
			listOrderDto.add(orderDTO);
			
		}
		return listOrderDto;
	}

	@Override
	public List<OrderDTO> listMyOrder1(Integer userId, Integer status) {

		if (-1 == status) {
			status = null;
		}
		
		List<Order> listOrder = orderMapper.listMyOrderByUserIdAndStatus(userId, status);
		List<OrderDTO> listOrderDto = new ArrayList<OrderDTO>(listOrder.size());
		for(Order order : listOrder) {
			OrderDTO orderDTO = new OrderDTO();
			
			orderDTO.setCreateTime(DateToString.to(order.getCreateTime()));
			orderDTO.setId(order.getId());
			orderDTO.setStatus(OrderStatusEnum.getEnum(order.getStatus()).getValue());
			orderDTO.setTotalPrice(order.getTotalPrice());
			orderDTO.setUserRealName(order.getUserRealName());
			
			List<OrderDetail> orderDetailList = orderDetailMapper.listOrderDetail(order.getId());
			List<OrderGoods> listOrderGoods = new ArrayList<OrderGoods>(orderDetailList.size());
			
			for (OrderDetail od : orderDetailList) {
				OrderGoods  orderGoods = new OrderGoods();
				orderGoods.setGoodsAccount(od.getGoodsAccount());
				orderGoods.setGoodsName(od.getGoodsName());
				orderGoods.setPrice(od.getPrice());
				
				listOrderGoods.add(orderGoods);
			}
			
			orderDTO.setOrderGoods(listOrderGoods);
			listOrderDto.add(orderDTO);
			
		}
		return listOrderDto;
	}

	@Override
	public PageUtils<OrderDTO> listMyOrder2(Integer userId, Integer status, Integer pageNum) {
		if (-1 == status) {
			status = null;
		}
		Integer orderTotal = orderMapper.getOrderTotal(userId, status);
		
		PageUtils<OrderDTO> pageUtils = new PageUtils<OrderDTO>(pageNum, orderTotal);
		
		List<Order> listOrder = orderMapper.listMyOrderPage(userId, status, pageUtils.getStartNum(), PageUtils.orderSize);
		List<OrderDTO> listOrderDto = new ArrayList<OrderDTO>(listOrder.size());
		for(Order order : listOrder) {
			OrderDTO orderDTO = new OrderDTO();
			
			orderDTO.setCreateTime(DateToString.to(order.getCreateTime()));
			orderDTO.setId(order.getId());
			orderDTO.setStatus(OrderStatusEnum.getEnum(order.getStatus()).getValue());
			orderDTO.setTotalPrice(order.getTotalPrice());
			orderDTO.setUserRealName(order.getUserRealName());
			
			List<OrderDetail> orderDetailList = orderDetailMapper.listOrderDetail(order.getId());
			List<OrderGoods> listOrderGoods = new ArrayList<OrderGoods>(orderDetailList.size());
			
			for (OrderDetail od : orderDetailList) {
				OrderGoods  orderGoods = new OrderGoods();
				orderGoods.setGoodsAccount(od.getGoodsAccount());
				orderGoods.setGoodsName(od.getGoodsName());
				orderGoods.setPrice(od.getPrice());
				
				listOrderGoods.add(orderGoods);
			}
			
			orderDTO.setOrderGoods(listOrderGoods);
			listOrderDto.add(orderDTO);
			
		}
		
		pageUtils.setData(listOrderDto);
		
		return pageUtils;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void processPayresult(Integer orderId, Integer payId) {
		Order order = orderMapper.getOrderForUpdate(orderId);
		if (null == order) {
			return;
		}
		if (OrderStatusEnum.UNPAY.getCode() != order.getStatus()) {
			return;
		}
		
		int rows = orderMapper.changeOrderStatus(orderId, order.getUserId(), OrderStatusEnum.PAID.getCode());
		
		if (1 != rows) {
			throw new ShopException("--订单更新待付款失败---");		
		}
		
		log.info("客户的订单支付成功的反馈 可以做发信息的反馈等业务逻辑");
		
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public PayOrderUrlDTO unPayOrderToPay(Integer userId, Integer orderId) {
		Order order = orderMapper.getOrderForUpdate(orderId);
		if (null == order) {
			throw new ShopException("--订单支付--- 查询商品表  ----- 失败");	
		}
		
		if (order.getUserId() != userId) {
			throw new ShopException("--订单不属于你  无权操作---");
		}
		
		if (order.getStatus() != OrderStatusEnum.UNPAY.getCode()) {
			throw new ShopException("--订单不能支付---");	
		}
		
		Date orderTime = order.getModifyTime();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderTime);
		cal.add(Calendar.MINUTE, 30);
		
		Date expDate = cal.getTime();
		
		if (new Date().after(expDate)) {
			throw new ShopException("--订单超时失效---");
		}
		
		//请求支付系统 获取支付地址
		PayOrder payOrder = new PayOrder();
		payOrder.setAmount(order.getTotalPrice());
		payOrder.setBankId(null);
		payOrder.setBizId(String.valueOf(order.getId()));
		payOrder.setDetailMsg("大猿商城");
		payOrder.setPayChannel(order.getPayChannel());
		payOrder.setUserId(order.getUserId());
		
		PayOrderUrlDTO payOrderUrlDTO = payService.addPayOrder(payOrder);
		
		return payOrderUrlDTO;
	}

	@Override
	public Order nowCreateOrder(Integer userId, Integer goodsId, Integer goodsAccount) {
		Goods goods  = goodsMapper.getGoodsById(goodsId);
		
		if (null == goods) {
			throw new ShopException("---- 查询商品  ----- 失败");	
		}
		
		if (goods.getStatus() != GoodsStatusEnum.GOODSUP.getCode()) {
			throw new ShopException(String.format("商品%s下架", goods.getGoodsName()));	
		}
		
		if (goods.getGoodsRepertory() < goodsAccount) {
			throw new ShopException(String.format("商品%s库存不足", goods.getGoodsName()));
		}
		
		String totalPrice = CalculateUtil.mul(String.valueOf(goodsAccount), goods.getPrice());
		
		Order order = new Order();
		order.setOrderFrom(OrderFromEnum.FROMGOOGSPAGE.getCode());
		order.setStatus(OrderStatusEnum.GOSETTLEMENT.getCode());
		order.setUserId(userId);
		order.setTotalPrice(totalPrice);
		
		int rows = orderMapper.createOrder(order);
		
		if (1 != rows) {
			throw new ShopException("--立即购买去结算失败---");		
		}
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setGoodsAccount(goodsAccount);
		orderDetail.setGoodsId(goodsId);
		orderDetail.setGoodsName(goods.getGoodsName());
		orderDetail.setGoodsTotalPrice(totalPrice);
		orderDetail.setOrderId(order.getId());
		orderDetail.setPrice(goods.getPrice());
		
		rows = orderDetailMapper.saveOrderDetail(orderDetail);
		
		if (1 != rows) {
			throw new ShopException("--立即购买----去结算--订单详情--失败---");		
		}
		
		return order; 
	}

	@Override
	public List<Order> listUnPayOrderForRollBack(Integer status) {
		List<Order> listUnPayOrderForRollBack = orderMapper.listOrderByUserIdAndStatus(null, status);
		return listUnPayOrderForRollBack;
	}

	

}
	
