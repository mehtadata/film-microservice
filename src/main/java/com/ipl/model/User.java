package com.ipl.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="users")
public class User {
	
	@Id
	public String username;
	
	public List<Film> films;
	
	public User(){}
	
	public User(String username){
		this.username = username;
	}
	
	public User(String username, List <Film> films){
		this.username = username;
		this.films = films;
	}
	
	public String asDetails(){
		return String.format("User[username='%s', films: (%s)]", username, 
				films.stream().map(film -> film.toString()).collect(Collectors.joining(",")));
	}

}
