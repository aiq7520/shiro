package org.gege.shiro.charpter3.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.Realm;


public class MyRealm2 implements Realm {

	@Override
	public String getName() {
		return "b";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return true;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		return new SimpleAuthenticationInfo(  
                "zhang", //身份 字符串类型  
                "123",   //凭据  
                getName() //Realm Name  
				 ); 
	}

	



}
