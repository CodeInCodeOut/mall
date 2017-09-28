package com.dayuanit.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.shop.domain.ShopCart;

public interface ShopCartMapper {

	int addShopCart(ShopCart shopCart);
	
	int changeGoodsNumById(@Param("goodsAccount")Integer goodsAccount, @Param("userId")Integer userId, @Param("shopCartId")Integer shopCartId);
	
	List<ShopCart> getShopCartByUserId(Integer userId);
	
	int changStatusById(@Param("status")Integer status, @Param("userId")Integer userId, @Param("shopCartId")Integer shopCartId);
}
