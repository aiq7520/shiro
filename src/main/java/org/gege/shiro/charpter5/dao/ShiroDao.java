package org.gege.shiro.charpter5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShiroDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	 //添加角色-权限之间关系  
    public int correlationPermissions(long roleId, long... permissionIds) {
    	Object[] params = new Object[permissionIds.length*2];
    	StringBuffer sql = new StringBuffer("INSERT INTO SYS_ROLES_PERMISSIONS(role_Id,permission_Id) VALUES ");
    	for (int i=0;i<permissionIds.length;i++){
    		params[2*i]=new Long(roleId);
    		params[2*i+1]=new Long(permissionIds[i]);
    		sql.append(" (?,?),");
    	}
    	if(permissionIds.length!=0)
    		sql.deleteCharAt(sql.lastIndexOf(",")).append(";");
    	return  jdbcTemplate.update(sql.toString(), params);
    }  
    //移除角色-权限之间关系  
    public int uncorrelationPermissions(long roleId, long... permissionIds){
    	Object[] params = new Object[permissionIds.length*2];
    	StringBuffer sql = new StringBuffer("DELETE FROM  SYS_ROLES_PERMISSIONS WHERE ");
    	for (int i=0;i<permissionIds.length;i++){
    		sql.append("( role_Id = ").append("?").append(" and permission_Id = ?)  or");
    		params[2*i]=new Long(roleId);
    		params[2*i+1]=new Long(permissionIds[i]);
    	}
    	if(permissionIds.length!=0)
    		sql.delete(sql.lastIndexOf("or"),sql.length()).append(";");
    	return jdbcTemplate.update(sql.toString(), params);
    }
  //添加用户-角色关系  
    public int correlationRoles(long userId, long... roleIds){
    	Object[] params = new Object[roleIds.length*2];
    	StringBuffer sql = new StringBuffer("INSERT INTO SYS_USERS_ROLES(user_Id,role_Id) VALUES ");
    	for (int i=0;i<roleIds.length;i++){
    		params[2*i]=new Long(userId);
    		params[2*i+1]=new Long(roleIds[i]);
    		sql.append(" (?,?),");
    	}
    	if(roleIds.length!=0)
    		sql.deleteCharAt(sql.lastIndexOf(",")).append(";");
    	return  jdbcTemplate.update(sql.toString(), params);
    } 
 // 移除用户-角色关系 
    public int uncorrelationRoles(long userId, long... roleIds){
    	Object[] params = new Object[roleIds.length*2];
    	StringBuffer sql = new StringBuffer("DELETE FROM  SYS_USERS_ROLES WHERE ");
    	for (int i=0;i<roleIds.length;i++){
    		sql.append("( user_Id = ").append("?").append(" and role_id = ?)  or");
    		params[2*i]=new Long(userId);
    		params[2*i+1]=new Long(roleIds[i]);
    	}
    	if(roleIds.length!=0)
    		sql.delete(sql.lastIndexOf("or"),sql.length()).append(";");
    	return jdbcTemplate.update(sql.toString(), params);
    }
}
