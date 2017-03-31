package com.ipl.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="users")
public class User {
	
	@Id
	@JsonProperty
	public String username;
	
	@JsonProperty
	public List<Film> films;
	
	public User(){
		
	}
	
	public User(String username){
		this.username = username;
	}
	
	public User(String username, List <Film> films){
		this.username = username;
		this.films = films;
	}
	
	public String asDetails(){
		return String.format("User[username='%s', films: (%s)]", username, 
				films.stream().map(Film::toString).collect(Collectors.joining(",")));
	}

}
