package com.ipl.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipl.model.Film;
import com.ipl.model.User;
import com.ipl.repository.UserRepository;

import io.swagger.annotations.ApiOperation;

@RestController
public class FilmRestController {

	@Autowired
	private UserRepository userRep;

	@ApiOperation(value="Returns all information for given user.")
	@RequestMapping(method = RequestMethod.GET, path = "/user")
	public User getUser(@RequestParam("username") String user) {
		return userRep.findByUsername(user);
	}

	@ApiOperation(value="Returns all users.")
	@RequestMapping(method = RequestMethod.GET, path = "/user/")
	public Iterable<User> getUsers() {
		return userRep.findAll();
	}
	
	@ApiOperation(value="Returns the list of films associated with the given user.")
	@RequestMapping(method = RequestMethod.GET, path = "/user/film/")
	public List<Film> getUsersFilms(@RequestParam(value = "username") String user) {
		return userRep.findByUsername(user).films;
	}

	@ApiOperation(value="Add a new user.")
	@RequestMapping(method = RequestMethod.POST, consumes="application/json", path = "/user")
	public void addUser(@RequestBody User user) {
		userRep.save(user);
	}

	@ApiOperation(value="Remove an existing user.")
	@RequestMapping(method = RequestMethod.DELETE, consumes="application/json",  path = "/user")
	public void deleteUser(@RequestBody User user) {
		userRep.delete(user);
	}
	
	@ApiOperation(value="Add new films to an existing users list.")
	@RequestMapping(method = RequestMethod.PATCH, consumes="application/json", path = "/user/film")
	public void addFilmsForUser(@RequestBody User user){
		userRep.addFilmsToUser(user);
	}
	
	@ApiOperation(value="Remove existing films from an existing users list.")
	@RequestMapping(method = RequestMethod.PATCH, consumes="application/json", path = "/user/film/rm")
	public void removeFilmsForUser(@RequestBody User user){
		userRep.removeFilmsFromUser(user);
	}

}
