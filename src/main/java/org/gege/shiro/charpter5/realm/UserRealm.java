package org.gege.shiro.charpter5.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.gege.shiro.charpter5.entity.Permission;
import org.gege.shiro.charpter5.entity.Role;
import org.gege.shiro.charpter5.entity.User;
import org.gege.shiro.charpter5.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
public class UserRealm extends  AuthorizingRealm {
	@Autowired
	private UserServices userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		SimpleAuthorizationInfo suthorizationInfo = new SimpleAuthorizationInfo();
		String username = (String)principals.getPrimaryPrincipal();
		User user = userService.findByUsername(username);  
		Set<Role> roles = user.getRoles();
		Set<String> roleNames = new HashSet<>();
		
		for (Role role : roles) {
			roleNames.add(role.getRole());
			Set<Permission> permissions = role.getPermissions();
			for (Permission permission : permissions) 
				suthorizationInfo.addStringPermission(permission.getPermission());
		}
		suthorizationInfo.addRoles(roleNames);
		return suthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		  User user = userService.findByUsername(username);  
	        if(user == null) {  
	            throw new UnknownAccountException();//没找到帐号  
	        }  
	        if(Boolean.TRUE.equals(user.getLocked())) {  
	            throw new LockedAccountException(); //帐号锁定  
	        }  
	        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现  
	        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(  
	                user.getUsername(), //用户名  
	                user.getPassword(), //密码  
	                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt  
	                getName()  //realm name  
	        ); 
	        return authenticationInfo;  
	}

}
