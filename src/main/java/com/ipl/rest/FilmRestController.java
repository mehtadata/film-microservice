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

@RestController
public class FilmRestController {

	@Autowired
	private UserRepository userRep;

	@RequestMapping(method = RequestMethod.GET, path = "/getFilmsForUser")
	public List<Film> getUsersFilms(@RequestParam(value = "user") String user) {
		return userRep.findByUsername(user).films;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/user")
	public User getUser(@RequestParam(value = "user") String user) {
		return userRep.findByUsername(user);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/getUsers")
	public List<User> getUsers() {
		return userRep.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, path = "/user")
	public void addUser(@RequestParam String username) {
		userRep.insert(new User(username));
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/user")
	public void deleteUser(@RequestParam String username) {
		userRep.deleteByUsername(username);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes="application/json", path = "/addFilmsForUser")
	public void addFilmsForUser(@RequestBody User user){
		userRep.addFilmsToUser(user);
	}

}
