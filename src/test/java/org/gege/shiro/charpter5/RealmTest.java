package org.gege.shiro.charpter5;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/shiro/charpter5/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RealmTest {
	@Autowired
	private ApplicationContext acx;
	private void login(String name ,String password) {
		
		SecurityManager securityManager = acx.getBean(SecurityManager.class);
		SecurityUtils.setSecurityManager(securityManager);
		//3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		subject.login(token);
	}
	
	   @Test
	    public void testCheckPermissionMyImpl () {  
	    	login("zhang","123");  
	        Subject subject = SecurityUtils.getSubject();
	        System.out.println(subject.isAuthenticated());
	    }
	   @Test
	   public void testHasRole () {  
		   login("zhang","123");  
		   Subject subject = SecurityUtils.getSubject();
		   Assert.assertEquals(true, subject.hasRole("admin"));
		   subject.checkPermission("user:create");
		   subject.checkPermission("user:update");
		   subject.checkPermission("menu:create");
	   }
	   @Test
	   public void checkPermission () {  
		   login("li","123");  
		   Subject subject = SecurityUtils.getSubject();
		   Assert.assertEquals(false, subject.hasRole("admin"));
		   Assert.assertEquals(true, subject.hasRole("user"));
		   subject.checkPermission("user:create");
		   subject.checkPermission("user:update");
		   subject.checkPermission("menu:create");//抛异常  没有该资源
	   }
	   
	   @Test
	    public void testCheckPass5 () {  
		   for (int i = 0; i < 5; i++) {
			   try {
					login("zhang","1");
				} catch (Exception e) {
					System.out.println("登录失败");
					e.printStackTrace();
				}  
		}
		   login("zhang","123");//前面已经登录5次失败，就算这次正确也登陆不上去
	        Subject subject = SecurityUtils.getSubject();
	        System.out.println(subject.isAuthenticated());
	    }
	   @Test
	    public void testCheckPass4 () {  
		   for (int i = 0; i < 4; i++) {
			   try {
					login("zhang","1");
				} catch (Exception e) {
					System.out.println("登录失败");
					e.printStackTrace();
				}  
		}
		   login("zhang","123");//前面已经登录4次失败
	        Subject subject = SecurityUtils.getSubject();
	        System.out.println(subject.isAuthenticated());
	    }
}
