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

import com.dayuanit.shop.domain.UserAddress;
import com.dayuanit.shop.mapper.UserAddressMapper;


@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserAdressTest {
	

	@Autowired
	private UserAddressMapper userAddressMapper; 
	
	private UserAddress userAddress;
	
	@Before
	public void init() {
		userAddress = new UserAddress();
		userAddress.setAreaCode("111");
		userAddress.setAreaName("000000");
		userAddress.setCityCode("000");
		userAddress.setCityName("99999");
		userAddress.setDtaAddress("0000");
		userAddress.setIsDefauAdress(1);
		userAddress.setPhone("11111");
		userAddress.setUserRealName("1111");
		userAddress.setUserId(1);
		userAddress.setProvinceCode("0000");
		userAddress.setProvinceName("000000");
	}
	
	@Rollback
	@Test
	public void testAdd() {
		int rows = userAddressMapper.addUserAddress(userAddress);
		assertEquals(1, rows);
	}
	
	@Rollback
	@Test
	public void testQueryUserAddressByUserId() {
		int rows = userAddressMapper.addUserAddress(userAddress);
		List<UserAddress> listUserAddress = userAddressMapper.listUserAddressByUserId(userAddress.getUserId());
		assertEquals(listUserAddress.size(), 1);
	}
	
	@Rollback
	@Test
	public void testQueryUserDefuAddressByUserId() {
		int rows = userAddressMapper.addUserAddress(userAddress);
		UserAddress ua = userAddressMapper.queryUserDefuAddressByUserId(userAddress.getUserId());
		assertEquals(ua.getAreaCode(), userAddress.getAreaCode());
	}
	
	
	@Rollback
	@Test
	public void testModifyAddress() {
		 userAddressMapper.addUserAddress(userAddress);
		 userAddress = new UserAddress();
		 userAddress.setAreaCode("1111");
		 userAddress.setAreaName("0000");
		 userAddress.setCityCode("0000");
		 userAddress.setDtaAddress("000");
		 userAddress.setIsDefauAdress(1);
		 userAddress.setPhone("12344");
		 userAddress.setProvinceCode("pppp");
		 userAddress.setUserRealName("00000");
		 userAddress.setProvinceName("999999");
		 int rows = userAddressMapper.modifyAddress(userAddress);
		 assertEquals(1, rows);
		
	}
	
	
	

}
