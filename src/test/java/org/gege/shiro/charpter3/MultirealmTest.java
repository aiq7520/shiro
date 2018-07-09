package org.gege.shiro.charpter3;

import java.util.Collections;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class MultirealmTest {
	private void login(String configFile,String name ,String password) {
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory =
				new IniSecurityManagerFactory(configFile);
		//2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		subject.login(token);
	}
	
	@Test
	public void testMultirealm(){
		login("classpath:shiro/charpter3/shiro-multirealm.ini","","");
		Subject subject = SecurityUtils.getSubject();
		Object primaryPrincipal1 = subject.getPrincipal();  
		System.out.println(primaryPrincipal1);
		PrincipalCollection princialCollection = subject.getPrincipals();  
		Object primaryPrincipal2 = princialCollection.getPrimaryPrincipal(); 
		System.out.println(primaryPrincipal2);
		Set<String> realmNames = princialCollection.getRealmNames();  	
		System.out.println(realmNames);
	    Set<Object> principals = princialCollection.asSet(); //asList和asSet的结果一样  
	    System.out.println(principals);
	}
}
