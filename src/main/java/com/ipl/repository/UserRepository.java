package com.ipl.repository;

import org.springframework.data.repository.CrudRepository;

import com.ipl.model.User;

public interface UserRepository extends CrudRepository<User, String>, CustomUserRepository {
	
	User findByUsername(String userName);
	
	void deleteByUsername(String userName);
	
}
