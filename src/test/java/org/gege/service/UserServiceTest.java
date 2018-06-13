package org.gege.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.gege.entity.User;
import org.gege.utils.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {
	@Autowired UserService userService;
	@Test
	public void testSaveUser() throws IOException{
		String filename = Utils.readProperity("file.path");
		List<String> lines  = FileUtils.readLines(new File(filename),  "UTF-8");
		List<User> users = new ArrayList<User>();
		for (String username : lines) 
			users.add(new User(username,"123456"));
		userService.saveAll(users);
		//throw new RuntimeException("test");
	}
}
