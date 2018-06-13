package org.gege.shiro.demo01;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shiro01Test {
	private static final transient Logger log = LoggerFactory.getLogger(Shiro01Test.class);
	/**
	 * 登录 / 退出
	 */
	@Test
	public void shiro001(){
		log.info("My First Apache Shiro Application");
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
	    Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shirouserdata.ini");

	    //2.得到SecurityManager实例 并绑定给SecurityUtils 
	    SecurityManager securityManager = factory.getInstance();
	    SecurityUtils.setSecurityManager(securityManager);
	    
	    //3.得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
	    Subject subject = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken("root", "secret");
	    try {
	        //4、登录，即身份验证
	        subject.login(token);
	    } catch (AuthenticationException e) {
	        //5、身份验证失败
	    	System.out.println(e.getClass());
	    }
	    Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
	    //6、退出
	    subject.logout();
	}
	/**
	 * 自定义realm
	 */
	@Test
	public void shiro002(){
		log.info("My Second Apache Shiro Application");
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shirosetrealm.ini");
		
		//2.得到SecurityManager实例 并绑定给SecurityUtils 
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		//3.得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("纪阁", "123456");
		try {
			//4、登录，即身份验证
			subject.login(token);
		} catch (AuthenticationException e) {
			//5、身份验证失败
			System.out.println(e.getClass()+" : "+e.getMessage());
		}
		Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
		//6、退出
		subject.logout();
	}
}
