package org.gege;

import java.util.List;

import org.gege.dao.Dao;
import org.gege.entity.Emp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringContextTest {
	@Autowired
	private Dao dao;
	@Test
	public void test() {
		List<? extends Object> listObj = dao.listObj(Emp.class);
		for (Object object : listObj) {
			Emp emp = (Emp)object;
			System.out.println(emp.toString());
		}
	}

}
