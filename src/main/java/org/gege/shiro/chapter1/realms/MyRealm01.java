package org.gege.shiro.chapter1.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.gege.utils.JedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRealm01 implements Realm {
	private static final transient Logger log = LoggerFactory.getLogger(MyRealm01.class);
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
		log.info("--into MyRealm01:getAuthenticationInfo method--");
		Object principal = token.getPrincipal();
		Object credentials = new String ((char[])token.getCredentials());
		String password = JedisUtils.jedis.hget("users", (String)principal);
		if(password==null)
			throw new UnknownAccountException("未知账户");
		if(!credentials.toString().equals(password))
			throw new CredentialsException("密码错误");
		//从redis缓存中获取用户列表进行验证
		AuthenticationInfo tokenInfo = new SimpleAuthenticationInfo(principal,credentials,getName());
		return tokenInfo;
	}
}
