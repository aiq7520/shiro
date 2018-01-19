package org.gege.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.gege.dao.Dao;
import org.gege.entity.Emp;
import org.gege.utils.PropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthenticatingRealm  {
	@Autowired private Dao dao;
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken =(UsernamePasswordToken) token;
		String name = upToken.getUsername();
		//从数据库取出name对应的用户记录
		Emp emp = dao.findObject("from Emp e where e.name = ? ",new Object[]{name},new BeanPropertyRowMapper<Emp>(Emp.class));
		
		if(null==emp) throw new UnknownAccountException("用户不存在!");
		if(emp.isLock())throw new LockedAccountException("用户被锁定");
		
		//密码验证交给shiro完成
		// 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
			//1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象. 
			Object principal = emp;
			//2). credentials: 密码. 
			Object credentials = emp.getPwd(); //"fc1709d0a95a6be30bc5926fdb7f22f4";
			//3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
			String realmName = getName();
			//4). 盐值. 加密密码时需要
			//ByteSource credentialsSalt = ByteSource.Util.bytes(usernam
		AuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realmName);//
		return info;
	}


}
