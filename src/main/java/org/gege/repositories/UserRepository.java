package org.gege.repositories;

import org.gege.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Integer> {
	public User getByUsername(String username);
}
