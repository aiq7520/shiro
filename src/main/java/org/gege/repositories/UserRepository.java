package org.gege.repositories;

import org.gege.entity.User;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass=User.class,idClass=Integer.class)
public interface UserRepository {
	public User getByUsername(String username);
	
}
