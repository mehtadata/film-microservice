package com.ipl.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.ipl.model.User;
import com.mongodb.WriteResult;

public class UserRepositoryImpl implements CustomUserRepository {

	@Autowired
	private MongoOperations operations;
	
	@Override
	public void addFilmsToUser(User user) {
		
		final Query query = new Query(Criteria.where("_id").is(user.username));
		Update update = new Update()
				.addToSet("films")
				.each(user.films);
		WriteResult wr = operations.updateFirst(query, update, CustomUserRepository.COLLECTION_NAME);
		System.out.println(wr);
	}

}
