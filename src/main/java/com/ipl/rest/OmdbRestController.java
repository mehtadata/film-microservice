package com.ipl.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipl.core.FilmRepository;
import com.ipl.core.exceptions.BadRequestException;
import com.ipl.core.exceptions.FilmNotFoundException;
import com.ipl.model.Film;

import io.swagger.annotations.ApiOperation;

@RestController
public class OmdbRestController {
	
	@Autowired
	FilmRepository fr;
	
	@ApiOperation(value="Returns all information held for given film title. Returns 404 if no film is found.")
	@RequestMapping(method = RequestMethod.GET, path = "/film", produces = "application/json")
	public Film getFilm(@RequestParam("title") String filmTitle) throws FilmNotFoundException {
		List<Film> foundFilms = fr.search(filmTitle);
		if (foundFilms.size() < 1) {
			throw new FilmNotFoundException(String.format("Could not find any film with title: %s", filmTitle));
		}
		return fr.searchById(foundFilms.get(0).getImdbID());
	}
	
	@ApiOperation(value="Returns an 'n' selection of randomly picked films")
	@RequestMapping(method = RequestMethod.GET, path = "/film/{n}", produces = "application/json")
	public List<Film> getRandomNFilms(@PathVariable int n) throws BadRequestException {
		List<Film> foundFilms = new ArrayList<>();
		final int maxRetry = 100;
		int tries = 0;
		if (n <= 0 || n > 20) {
			throw new BadRequestException("Path variable number must be a value of 1-20 inclusive."); 
		}
		
		while (foundFilms.size() < n) {
			try {
				Film f = fr.searchById(getRandomId());
				foundFilms.add(f);
			} catch (FilmNotFoundException e) {
				if(++tries >= maxRetry){
					throw new BadRequestException("Failed to find enough random films.");
				}
			}
		}
		
		return foundFilms;
	}
	
	private String getRandomId() {
		String id = "tt";
		int randomNum = ThreadLocalRandom.current().nextInt(1, 999999);
		return id + String.format("%07d", randomNum);
	}
	

}
