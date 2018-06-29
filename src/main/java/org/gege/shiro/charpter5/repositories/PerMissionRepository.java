package org.gege.shiro.charpter5.repositories;

import org.gege.shiro.charpter5.entity.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
@Transactional(readOnly=true)
public interface PerMissionRepository  extends CrudRepository<Permission,Integer> {
	
	 
}
