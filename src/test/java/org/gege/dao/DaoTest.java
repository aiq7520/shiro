package org.gege.dao;

import org.gege.entity.Emp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoTest {
	@Autowired
	private Dao dao;
//	@Autowired
//	private Service services;
	@Test
	public void test1(){
		
		Emp emp =null;
		try {
			emp = dao.findObject("from Emp  where name = ? ",new Object[]{"ss"},new BeanPropertyRowMapper<Emp>(Emp.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(emp);
	}
}

