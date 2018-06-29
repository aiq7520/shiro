package org.gege.shiro.chapter2;

import java.util.Iterator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

public class AuthenticatorTest {
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
	   private void login(String configFile) {
		   String name ="zhang";
		   String password = "123";
		   login(configFile,name,password);
	    }
	    @After
	    public void tearDown() throws Exception {
	        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
	    }
	    
	    /**
	     * 测试策略为 AllSuccessfulStrategy全部通过  的失败情况
	     */
	    @Test
	    public void testAllSuccessfulStrategyWithFail() {
	        login("classpath:shiro/charpter2/shiro-authenticator-all-fail.ini");
	        Subject subject = SecurityUtils.getSubject();
	    	 PrincipalCollection principals = subject.getPrincipals();
	    	 Iterator<?> iterator = principals.iterator();
	    	 while(iterator.hasNext()){
	    		System.out.println(iterator.next());//zhang
	    	 }
	    }
	    
	    /**
	     * 测试策略为 AllSuccessfulStrategy全部通过  的成功情况
	     */
	    @Test
	    public void testAllSuccessfulStrategyWithSuccess() {
	    	login("classpath:shiro/charpter2/shiro-authenticator-all-success.ini");
	    	 Subject subject = SecurityUtils.getSubject();
	    	 PrincipalCollection principals = subject.getPrincipals();
	    	 Iterator<?> iterator = principals.iterator();
	    	 while(iterator.hasNext())
	    		System.out.println(iterator.next());//zhang  zhang@163.com
	    }
	    
	    /**
	     * 策略至少有一个 成功的情况
	     */
	    @Test
	    public void testAtLeastOneSuccessfulStrategyWithSuccess() {
	    	login("classpath:shiro/charpter2/shiro-authenticator-atleastone-success.ini","wang","123");
	    	Subject subject = SecurityUtils.getSubject();
	    	PrincipalCollection principals = subject.getPrincipals();
	    	Iterator<?> iterator = principals.iterator();
	    	while(iterator.hasNext())
	    		System.out.println(iterator.next());
	    }
	    
	    /**
	     * 策略首个成功的情况
	     */
	    @Test
	    public void testFirstSuccessfulStrategyWithFai1l() {
	    	login("classpath:shiro/charpter2/shiro-authenticator-first-fail.ini","zhang","123");
	    	Subject subject = SecurityUtils.getSubject();
	    	PrincipalCollection principals = subject.getPrincipals();
	    	Iterator<?> iterator = principals.iterator();
	    	while(iterator.hasNext())
	    		System.out.println(iterator.next());
	    }
	    
	    /**
	     * 自定义两个成功的情况
	     */
	    @Test
	    public void testFirstSuccessfulStrategyWithSuccess() {
	    	login("classpath:shiro/charpter2/shiro-authenticator-atleasttwo-fail.ini","zhang","123");
	    	Subject subject = SecurityUtils.getSubject();
	    	PrincipalCollection principals = subject.getPrincipals();
	    	Iterator<?> iterator = principals.iterator();
	    	while(iterator.hasNext())
	    		System.out.println(iterator.next());
	    }
	    
}
