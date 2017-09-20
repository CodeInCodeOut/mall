package com.dayuanit.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.shop.domain.Goods;

public interface GoodsMapper {
	
	int addGoods(Goods goods);

	Goods getGoodsById(Integer goodsId);
	
	List<Goods> getAllGoodsBySortId(Integer sortId);
	
	int changeGoodsRepertory(@Param("goodsAccount")Integer goodsAccount, @Param("goodsId")Integer goodsId);
	
	Goods getGoodsByIdForUpdate(Integer goodsId);
}
