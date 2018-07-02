package org.gege.shiro.charpter5.service;

import javax.transaction.Transactional;

import org.gege.shiro.charpter5.entity.Permission;
import org.gege.shiro.charpter5.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;
	public Permission  createPermission(Permission p) {
		return permissionRepository.save(p);
	}

}
