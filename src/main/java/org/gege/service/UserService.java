package org.gege.service;


import org.gege.entity.User;
import org.gege.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class UserService {
	@Autowired UserRepository userRepository;
	
	public void saveAll(Iterable<User> iterator){
		userRepository.saveAll(iterator);
	}
}
