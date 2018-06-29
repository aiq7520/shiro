package org.gege.shiro.charpter3.realms;

import java.util.Set;
import java.util.TreeSet;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		Set<String> roles =new TreeSet<String>();
		roles.add("role1");
		roles.add("role2");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles); 
		Set<String> stringPermissions = new TreeSet<>();
		stringPermissions.add("user:create");
		authorizationInfo.setStringPermissions(stringPermissions );
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
	    String password = new String((char[])token.getCredentials()); //得到密码
	    String username = (String)token.getPrincipal();  //得到用户名
        if(!"zhang".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName()); 
	}

}
