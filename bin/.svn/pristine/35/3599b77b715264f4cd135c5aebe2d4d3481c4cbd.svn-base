package shop;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.shop.domain.User;
import com.dayuanit.shop.mapper.UserMapper;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserMapperTest {
	
	@Autowired
	private UserMapper userMapper;
	
	private User user;
	
	@Before
	public void init() {
		user = new User();
		user.setEmail("9999");
		user.setUserName("0000");
		user.setPassword("00000");
		user.setStatus(1);
		
	}
	
	@Rollback
	@Test
	public void testAddUser() {
		int rows = userMapper.addUser(user);
		assertEquals(1, rows);
	}

	@Rollback
	@Test
	public void testQueryUserByUserName() {
		userMapper.addUser(user);
		User exitUser = userMapper.queryUserByUserName(user.getUserName());
		assertEquals(exitUser.getUserName(), user.getUserName());
	}
	
	@Rollback
	@Test
	public void testQueryUserByUserId() {
		userMapper.addUser(user);
		User exitUser = userMapper.queryUserByUserId(user.getId());
		assertEquals(exitUser.getUserName(), user.getUserName());
	}
}
