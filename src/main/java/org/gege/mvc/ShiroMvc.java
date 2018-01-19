package org.gege.mvc;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShiroMvc {
	
	@RequestMapping("login")
	public void login(String name ,String pwd){
		//认证
		UsernamePasswordToken token = new UsernamePasswordToken( name, pwd );
		token.setRememberMe(true);
		Subject currentUser = SecurityUtils.getSubject();
		String  msg = "登录成功";
		
			currentUser.login(token);
	}
}
