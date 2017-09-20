package com.dayuanit.shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.shop.domain.ShopCart;
import com.dayuanit.shop.exception.ShopException;
import com.dayuanit.shop.mapper.ShopCartMapper;
import com.dayuanit.shop.service.ShopCartService;


@Service
public class ShopCartServiceImpl implements ShopCartService {
	
	@Autowired
	private ShopCartMapper shopCartMapper;
	

	@Override
	public void increaseGoodsToShopCart(Integer userId, Integer goodsId, Integer goodsAccount) {
		// TODO Auto-generated method stub
		
		ShopCart shopCart = new ShopCart();
		shopCart.setStatus(1);
		shopCart.setGoodsId(goodsId);
		shopCart.setGoodsAccount(goodsAccount);
		shopCart.setUserId(userId);
		
		int rows = shopCartMapper.addShopCart(shopCart);
		
		if (1 != rows) {
			throw new ShopException("客户增加商品到购物车失败");
		}
		
	}

	
	@Override
	public List<ShopCart> getShopCart(Integer userId) {
		// TODO Auto-generated method stub
		
		List<ShopCart> userShopCart= shopCartMapper.getShopCartByUserId(userId);
		
		if (null == userShopCart) {
			throw new ShopException("查询客户购物车所有的商品失败");
		}
		
		return userShopCart;
	}

	@Override
	public void changeGoodsNumInShopCart(Integer userId, Integer goodsAccount, Integer shopCart) {
		// TODO Auto-generated method stub
		
		int rows = shopCartMapper.changeGoodsNumById(goodsAccount, userId, shopCart);
		
		if (1 != rows) {
			throw new ShopException("客户改变商品数量到购物车失败");
		}
		
	}

	@Override
	public void deleteGoodsById(Integer userId, Integer shopCartId) {
		// TODO Auto-generated method stub
		
		Integer status = 4;
		int rows = shopCartMapper.changStatusById(status, userId, shopCartId);
		
		if (1 != rows) {
			throw new ShopException("客户删除商品 在购物车 失败");
		}
		
	}

}
