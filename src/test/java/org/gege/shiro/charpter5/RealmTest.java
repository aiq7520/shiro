package org.gege.shiro.charpter5;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
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
	    	login("张三","456");  
	        Subject subject = SecurityUtils.getSubject();
	        System.out.println(subject.isAuthenticated());
	    }
}
