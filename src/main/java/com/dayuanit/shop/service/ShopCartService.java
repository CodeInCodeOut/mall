package com.dayuanit.shop.service;

import java.util.List;

import com.dayuanit.shop.domain.ShopCart;

public interface ShopCartService {
	
	void increaseGoodsToShopCart(Integer userId, Integer goodsId, Integer goodsAccount);
	
	List<ShopCart> getShopCart(Integer userId);
	
	void changeGoodsNumInShopCart(Integer userId, Integer goodsAccount, Integer shopCart);
	
	void deleteGoodsById(Integer userId, Integer shopCartId);
	

}
