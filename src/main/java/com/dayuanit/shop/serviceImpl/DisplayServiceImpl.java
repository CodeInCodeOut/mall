package com.dayuanit.shop.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.shop.domain.Goods;
import com.dayuanit.shop.domain.GoodsSort;
import com.dayuanit.shop.exception.ShopException;
import com.dayuanit.shop.mapper.GoodsMapper;
import com.dayuanit.shop.mapper.GoodsSortMapper;
import com.dayuanit.shop.service.DisplayService;

@Service
public class DisplayServiceImpl implements DisplayService{
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private  GoodsSortMapper goodsSortMapper;

	@Override
	public Map<String, Object> display(Integer sortId) {
		// TODO Auto-generated method stub
		List<GoodsSort> listSort = goodsSortMapper.getGoodsSort();
		
		List<Goods> listGoods = goodsMapper.getAllGoodsBySortId(sortId);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("listSort", listSort);
		map.put("listGoods", listGoods);
		
		return map;
	}

	@Override
	public List<GoodsSort> listSort() {
		// TODO Auto-generated method stub
		List<GoodsSort> listSort = goodsSortMapper.getGoodsSort();
		
		if (null == listSort) {
			throw new ShopException("商品分类查询失败");
		}
		
		return listSort;
	}

	@Override
	public List<Goods> listGoods(Integer sortId) {
		// TODO Auto-generated method stub
		
		List<Goods> listGoods = goodsMapper.getAllGoodsBySortId(sortId);
		
		if (null == listGoods) {
			throw new ShopException("类目下商品查询失败");
		}
		
		
		return listGoods;
	}

	@Override
	public Goods getGoodsById(Integer goodsId) {
		// TODO Auto-generated method stub
		Goods goods = goodsMapper.getGoodsById(goodsId);
		
		if (null == goods) {
			throw new ShopException("具体某一个商品商品查询失败");
		}
		
		return goods;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void subGoodsRepertory(Integer goodsAccount, Integer goodsId) {
		Goods goods = goodsMapper.getGoodsByIdForUpdate(goodsId);
		
		if (null == goods) {
			throw new ShopException(String.format("商品%s查询失败", goodsId));
		}
		
		int rows = goodsMapper.changeGoodsRepertory(-goodsAccount, goodsId);
		
		if (1 != rows) {
			throw new ShopException("减库存失败");
		}
	}

	
	

	
}
