package org.gege.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Dao  {
	@Autowired private HibernateTemplate hibernateTemplate;
	@Autowired private JdbcTemplate jdbcTemplate;
	public void save(Object o){
		hibernateTemplate.saveOrUpdate(o);
	}
	public List<? extends Object> listObj(Class<?> clazz){
		String hql ="from "+ clazz.getName();
		return hibernateTemplate.find(hql);
	}
	
	public <T> T  findObject(String hql,Object... params ){
		return jdbcTemplate.queryForObject(hql, params,new BeanPropertyRowMapper<T>());
	}
}
