package org.gege.shiro.charpter5.repositories;

import java.util.List;

import org.gege.shiro.charpter5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly=true)
public interface UserRepository extends JpaRepository<User,Integer>{

	@Modifying
	@Query("update User u set u.password = ?2 where u.id = ?1")
	public void changePassword(Integer id, String password);//修改密码  
	public User findByUsername(String username);// 根据用户名查找用户  
	public List<String> findRolesByUsername(String username);// 根据用户名查找其角色 
	@Query("select u.roles from User u where u.username=?1")
	public List<String> findRoles(String username);// 根据用户名查找其角色 
}
