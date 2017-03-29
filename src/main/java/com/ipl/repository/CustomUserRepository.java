package com.ipl.repository;

import com.ipl.model.User;

public interface CustomUserRepository {
	
	void addFilmsToUser(User user);
	
	void removeFilmsFromUser(User user);

}
