package org.gege.shiro.charpter3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AuthorizerTest {
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
    public void testCheckPermission () {  
    	login("classpath:shiro/charpter3/shiro-roles-permission.ini","zhang","123");  
        Subject subject = SecurityUtils.getSubject();
        //断言拥有权限：user:create  
        subject.checkPermission("user:create");
        //断言拥有权限：user:delete and user:update  
        subject.checkPermissions("user:delete", "user:update");  
        //断言拥有权限：user:view 失败抛出异常  
//        subject.checkPermissions("user:view"); 
    }
    @Test
    public void testCheckPermissionMyImpl () {  
    	login("classpath:shiro/charpter3/shiro-roles-permission-impl.ini","zhang","123");  
        Subject subject = SecurityUtils.getSubject();
        //断言拥有权限：user:create  
        subject.checkPermission("user:create");
        //断言拥有权限：user:delete and user:update  
//        subject.checkPermissions("user:delete", "user:update");  
        //断言拥有权限：user:view 失败抛出异常  
//        subject.checkPermissions("user:view"); 
    }
}
