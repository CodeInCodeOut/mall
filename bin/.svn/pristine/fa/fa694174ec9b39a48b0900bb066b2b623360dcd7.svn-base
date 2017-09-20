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

import com.dayuanit.shop.domain.ShopCart;
import com.dayuanit.shop.mapper.ShopCartMapper;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ShopCartMapperTest {
	
	@Autowired
	private  ShopCartMapper shopCartMapper;
	
	private ShopCart shopCart;
	
	@Before
	public void init() {
		shopCart = new ShopCart();
		shopCart.setGoodsId(111);
		shopCart.setGoodsAccount(2);
		shopCart.setUserId(1);
		shopCart.setStatus(1);
	}
	
	@Rollback
	@Test
	public void testAddShopCart() {
		int rows = shopCartMapper.addShopCart(shopCart);
		assertEquals(1, rows);
	}
	
	@Rollback
	@Test
	public void testGetShopCart() {
		int rows = shopCartMapper.addShopCart(shopCart);
		List<ShopCart> scList = shopCartMapper.getShopCartByUserId(shopCart.getUserId());
		assertEquals(scList.size(), 1);
	}
	

	@Rollback
	@Test
	public void testChangeGoodsNumById() {
		shopCartMapper.addShopCart(shopCart);
		int rows = shopCartMapper.changeGoodsNumById(shopCart.getGoodsAccount(), shopCart.getUserId(), shopCart.getId());
		assertEquals(1, rows);
	}
	
	@Rollback
	@Test
	public void testChangeStatus() {
		shopCartMapper.addShopCart(shopCart);
		int rows = shopCartMapper.changeGoodsNumById(4, shopCart.getUserId(), shopCart.getId());
		assertEquals(1, rows);
	}

}
