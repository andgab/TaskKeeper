
package com.taskkeeper.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.taskkeeper.persistence.domain.User;

public interface UsersRepository extends CrudRepository<User, Long> {
	
	User findByUsername(String username);

}
