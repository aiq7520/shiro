package org.gege.repositories;

import org.gege.entity.User;
import org.gege.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserReponsitoryTest {
	@Autowired UserRepository userRepository;
	@Test
	public void testGetByUsername(){
		User user = userRepository.getByUsername("zhangsan");
		System.out.println(user);
	}
}
