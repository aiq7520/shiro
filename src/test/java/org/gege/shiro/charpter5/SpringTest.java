package org.gege.shiro.charpter5;

import java.sql.SQLException;

import org.gege.shiro.charpter5.dao.ShiroDao;
import org.gege.shiro.charpter5.entity.Role;
import org.gege.shiro.charpter5.repositories.RoleRepository;
import org.gege.shiro.charpter5.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration("/shiro/charpter5/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTest {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShiroDao shiroDao;
	@Test
	public void correlationPermissions() throws SQLException {
			Assert.assertEquals(3,shiroDao.correlationPermissions(1,1,2,3));
	}
	@Test
	public void uncorrelationPermissions() {
		Assert.assertEquals(3,shiroDao.uncorrelationPermissions(1,1,2,3));
	}

	@Test
	public void testR(){
		Role role= new Role("role1", "rol1e", true);
		roleRepository.save(role);
	}
	
	@Test
	public void findRoles(){
		roleRepository.findRoles("");
	}
	@Test
	public void findRoles1(){
		userRepository.findRoles("");
		userRepository.findRolesByUsername("");
	}
	
}
