package org.gege.dao;

import org.gege.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDao {
	@Autowired private JdbcTemplate jdbcTemplate;
	@Autowired private HibernateTemplate hibernateTemplate;
	public Emp load(String name, String pwd) {
		Object[] params = {name,pwd};
		return jdbcTemplate.queryForObject("from Emp e where e.name=? and e.pwd=?", params,new BeanPropertyRowMapper<Emp>(Emp.class));
	}
}
