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

import com.dayuanit.shop.domain.Order;
import com.dayuanit.shop.mapper.OrderMapper;


@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OrderMapperTest {
	
	
	@Autowired
	private OrderMapper orderMapper;
	
	
	private Order order;
	
	
	@Before
	public void init() {
		order.setUserId(1);
		order.setOrderFrom(1);
		order.setStatus(1);
		order.setTotalPrice("uuuu");
		
	}
	
	@Rollback
	@Test
	public void testPage() {
		Integer startNum = 0;
		Integer orderSize = 2;
		orderMapper.createOrder(order);
		List<Order> list = orderMapper.listMyOrderPage(order.getUserId(), order.getStatus(), startNum, orderSize);
		assertEquals(1, list.size());
	}
	
//	@Rollback
//	@Test
//	public void testCreateOrder() {
//		int rows = orderMapper.createOrder(order);
//		assertEquals(1, rows);
//	}
//	
//	
//	@Rollback
//	@Test
//	public void testListOrderByUserIdAndStatus() {
//		orderMapper.createOrder(order);
//	}
//	
//	@Rollback
//	@Test
//	public void testChangeOrderStatus() {
//		orderMapper.createOrder(order);
//	}

}
