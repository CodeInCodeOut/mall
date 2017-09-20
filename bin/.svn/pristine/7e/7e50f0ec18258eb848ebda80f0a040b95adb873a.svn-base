package shop;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.shop.domain.GoodsSort;
import com.dayuanit.shop.mapper.GoodsSortMapper;


@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class GoodsSortMapperTest {
	
	@Autowired
	private GoodsSortMapper goodsSortMapper;
	
	private GoodsSort goodsSort;
	
	
	@Before
	public void init() { 
		goodsSort = new GoodsSort();
		goodsSort.setSortName("222");
		goodsSort.setStatus(1);
		
	}
	
	@Rollback
	@Test
	public void testAddSort() {
		int rows = goodsSortMapper.addGoodsSort(goodsSort);
		assertEquals(1, rows);
	}
	
	
	@Rollback
	@Test
	public void testGetSort() {
		int rows = goodsSortMapper.addGoodsSort(goodsSort);
		List<GoodsSort> listSort = goodsSortMapper.getGoodsSort();
		assertEquals(listSort.size(), 1);
	}
	

}
