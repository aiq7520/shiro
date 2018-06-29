package org.gege.shiro.charpter5.service;

import javax.transaction.Transactional;

import org.gege.shiro.charpter5.entity.Role;
import org.gege.shiro.charpter5.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	public Role save( Role role){
		return roleRepository.save(role);
	}
}
