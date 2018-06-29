package org.gege.shiro.charpter5.repositories;


import java.util.Set;

import org.gege.shiro.charpter5.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
@Transactional(readOnly=true)
public interface RoleRepository  extends CrudRepository<Role,Integer> {

	/**
	 * 原生sql语句 查询
	 * @param username
	 * @return
	 */
	@Query(value="SELECT role FROM SYS_ROLES WHERE id in ( SELECT role_id FROM SYS_USERS_ROLES WHERE user_id IN (SELECT id FROM SYS_USERS WHERE username =? ))",nativeQuery=true)
	public Set<String> findRoles(String username);// 根据用户名查找其角色 
	
}
