package com.ipl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ipl.model.User;

public interface UserRepository extends MongoRepository<User, String>, CustomUserRepository {
	
	public User findByUsername(String userName);
	
	public void deleteByUsername(String userName);
	
}
