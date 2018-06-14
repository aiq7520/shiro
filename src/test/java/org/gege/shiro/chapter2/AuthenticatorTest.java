package org.gege.shiro.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

public class AuthenticatorTest {
	   private void login(String configFile) {
	        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
	        Factory<org.apache.shiro.mgt.SecurityManager> factory =
	                new IniSecurityManagerFactory(configFile);

	        //2、得到SecurityManager实例 并绑定给SecurityUtils
	        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
	        SecurityUtils.setSecurityManager(securityManager);

	        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
	        Subject subject = SecurityUtils.getSubject();
	        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
	        subject.login(token);
	    }
	    @After
	    public void tearDown() throws Exception {
	        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
	    }
	    
	    @Test(expected = UnknownAccountException.class)
	    public void testAllSuccessfulStrategyWithFail() {
	        login("classpath:shiro-authenticator-all-fail.ini");
	    }
	    
}
