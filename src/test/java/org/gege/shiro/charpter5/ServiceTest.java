package org.gege.shiro.charpter5;

import java.sql.SQLException;

import org.gege.shiro.charpter5.entity.User;
import org.gege.shiro.charpter5.repositories.UserRepository;
import org.gege.shiro.charpter5.service.ShiroService;
import org.gege.shiro.charpter5.service.UserServices;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration("/shiro/charpter5/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceTest {

	@Autowired
	private ShiroService shiroService;
	@Autowired
	private UserServices userService;
	@Autowired
	private UserRepository userRepository;
	@Test
	public void correlationPermissions() throws SQLException {
			Assert.assertEquals(3,shiroService.correlationPermissions(1,1l,2l,3l));
	}
	@Test
	public void uncorrelationPermissions() {
		Assert.assertEquals(3,shiroService.uncorrelationPermissions(1,1l,2l,3l));
	}
	
	/***user Test***/
	@Test
	public void testTreateUser() {
		User u = new User("张三","123");
		userService.save(u);
	}
	@Test
	public void testChangePassword() {
		userService.changePassword(1, "456");
	}
}
