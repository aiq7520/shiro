package org.gege.shiro.chapter1;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
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
	
	public void login(String path,String name ,String pass){
		log.info("My First Apache Shiro Application");
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
	    Factory<SecurityManager> factory = new IniSecurityManagerFactory(path);
	    //2.得到SecurityManager实例 并绑定给SecurityUtils 
	    SecurityManager securityManager = factory.getInstance();
	    SecurityUtils.setSecurityManager(securityManager);
	  //3.得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
	    Subject subject = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken(name,pass);
	    try {
	        //4、登录，即身份验证
	        subject.login(token);
	    } catch (AuthenticationException e) {
	        //5、身份验证失败
	    	System.out.println(e.getClass());
	    }
	}
	
	/**
	 * 登录 / 退出
	 */
	@Test
	public void shiro001(){
		log.info("My First Apache Shiro Application");
		//1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager  
	    Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/charpter/shirouserdata.ini");

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
//		login("classpath:shiro/shirosetrealm.ini","纪阁", "123456");//realm全部通过
//		login("classpath:shiro/shirosetrealm.ini","刘冬霞", "123456");//第一个通过
		login("classpath:shiro/charpter/shirosetrealm.ini","刘冬霞", "123456");//第一个通过
		Assert.assertEquals(true,  SecurityUtils.getSubject().isAuthenticated()); //断言用户已经登录
		//6、退出
		 SecurityUtils.getSubject().logout();
	}
	
	/**
	 * 授权
	 */
	@Test(expected = UnauthorizedException.class)  
	public void shiro003(){
		login("classpath:shiro/charpter/shirouserdata.ini","root", "secret");
		log.info("My First Apache Shiro Application");
	    Assert.assertEquals(true,  SecurityUtils.getSubject().isAuthenticated()); //断言用户已经登录
	    
	    //判断拥有角色：admin  
	    Assert.assertTrue( SecurityUtils.getSubject().hasRole("admin"));  
	    //判断拥有角色：role1 and role2  
	    Assert.assertFalse( SecurityUtils.getSubject().hasAllRoles(Arrays.asList("admin", "role2")));  
	    
	    //判断拥有角色：role1 and role2 and !role3  
	    boolean[] result =  SecurityUtils.getSubject().hasRoles(Arrays.asList("admin", "role2", "role3"));  
	    Assert.assertEquals(true, result[0]);  
	    Assert.assertEquals(false, result[1]);  
	    Assert.assertEquals(false, result[2]);  
	    
	    //如果没有对应的角色将报异常
	    SecurityUtils.getSubject().checkRole("admin");
	    SecurityUtils.getSubject().checkRoles("admin", "role3");  
	    //6、退出
	    SecurityUtils.getSubject().logout();
	}
}
