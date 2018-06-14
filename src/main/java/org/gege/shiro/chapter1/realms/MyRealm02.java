package org.gege.shiro.chapter1.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRealm02 implements Realm {
	private static final transient Logger log = LoggerFactory.getLogger(MyRealm02.class);
	@Override
	public String getName() {
		return this.getClass().getName();
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		log.info("--into MyRealm02:getAuthenticationInfo method--");
		Object principal = token.getPrincipal();
		Object credentials = new String ((char[])token.getCredentials());
		
		//第二个reaml 
		
		if(!"shining".equals(principal))
				throw new UnknownAccountException("未知账户");
			if(!"123456".toString().equals(credentials))
				throw new CredentialsException("密码错误");
			
		//从redis缓存中获取用户列表进行验证
		AuthenticationInfo tokenInfo = new SimpleAuthenticationInfo("shining","123456",getName());
		return tokenInfo;
	}
}
