package org.gege.shiro.charpter5.service;

import javax.transaction.Transactional;

import org.gege.shiro.charpter5.entity.User;
import org.gege.shiro.charpter5.repositories.UserRepository;
import org.gege.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServices {

	@Autowired
	private UserRepository userRepository;
	PasswordHelper passwordHelper = new PasswordHelper();
	
	public User save(User u){
		 //加密密码  
	    passwordHelper.encryptPassword(u);  
		return userRepository.save(u);
	}
	
	public void changePassword(Integer userId,String newPassword){
			User user =userRepository.getOne(userId);
		    user.setPassword(newPassword);  
		    passwordHelper.encryptPassword(user);  
		    userRepository.save(user);
	}
	public User findByUsername(String username){
		return userRepository.findByUsername(username);
	}
}
