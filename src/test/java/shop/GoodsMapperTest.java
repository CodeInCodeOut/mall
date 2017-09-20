package shop;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.shop.domain.Goods;
import com.dayuanit.shop.mapper.GoodsMapper;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class GoodsMapperTest {
	
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	private Goods goods;
	
	@Before
	public void init() {
		goods = new Goods();
		goods.setGoodsDesc("nnnnn");
		goods.setGoodsImg("99999");
		goods.setGoodsName("wwww");
		goods.setGoodsRepertory(67);
		goods.setPrice("999");
		goods.setSoldNum(98);
		goods.setStatus(1);
		goods.setSortId(1);
	
		
		
	} 
	
	
	@Rollback
	@Test
	public void testAdd() {
		int rows = goodsMapper.addGoods(goods);
		assertEquals(1, rows);
	}
	
	@Rollback
	@Test
	public void testGetGoodsById() {
		int rows = goodsMapper.addGoods(goods);
		Goods gs = goodsMapper.getGoodsById(goods.getId());
		assertEquals(gs.getGoodsName(), goods.getGoodsName());
	}
	
	
	@Rollback
	@Test
	public void testGetAllGoodsBySortId() {
		int rows = goodsMapper.addGoods(goods);
		List<Goods> listGoods = goodsMapper.getAllGoodsBySortId(goods.getSortId());
		
		assertEquals(listGoods.size(), 1);
		
	}
	
	@Rollback
	@Test
	public void testChangeGoodsRepertory() {
		goodsMapper.addGoods(goods);
		int goodsAccount = 1;
		int rows = goodsMapper.changeGoodsRepertory(goodsAccount, goods.getId());
		assertEquals(1, rows);
	}
	
	

}
