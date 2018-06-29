package org.gege.shiro.charpter5.service;

import java.sql.SQLException;

import javax.transaction.Transactional;

import org.gege.shiro.charpter5.dao.ShiroDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ShiroService {

	@Autowired
	private ShiroDao shiroDao;
	 //添加角色-权限之间关系  
    public int correlationPermissions(long roleId, long... permissionIds) throws SQLException{
    	return shiroDao.correlationPermissions(roleId, permissionIds);
    }  
    //移除角色-权限之间关系  
    public int uncorrelationPermissions(long roleId, long... permissionIds){
    	return shiroDao.uncorrelationPermissions(roleId, permissionIds);
    }
}
