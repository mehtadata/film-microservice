package com.ipl.repository.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.ipl.model.User;
import com.ipl.repository.CustomUserRepository;

public class UserRepositoryImpl implements CustomUserRepository {
	
	private final static String COLLECTION_NAME = "users";

	@Autowired
	private MongoOperations operations;
	
	@Override
	public void addFilmsToUser(User user) {
		
		final Query query = new Query(Criteria.where("_id").is(user.username));
		Update update = new Update()
				.addToSet("films")
				.each(user.films);
		operations.updateFirst(query, update, COLLECTION_NAME);
	}
	
	@Override
	public void removeFilmsFromUser(User user) {
		final Query query = new Query(Criteria.where("_id").is(user.username));
		Update update = new Update().pullAll("films", user.films.toArray());
		operations.updateFirst(query, update, COLLECTION_NAME);
	}

}
