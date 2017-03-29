package com.ipl.repository;

import com.ipl.model.User;

public interface CustomUserRepository {
	
	final static String COLLECTION_NAME = "users";
	
	void addFilmsToUser(User user);

}
