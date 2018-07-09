package org.gege.shiro.charpter3.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.Realm;
import org.gege.shiro.charpter5.entity.User;


public class MyRealm3 implements Realm {

	@Override
	public String getName() {
		return "c";
	}


	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		User user = new User("zhang", "123");  
        return new SimpleAuthenticationInfo(  
                user, //身份 User类型  
                "123",   //凭据  
                getName() //Realm Name  
        );  
	}


	@Override
	public boolean supports(AuthenticationToken token) {
		return true;
	}

	



}
