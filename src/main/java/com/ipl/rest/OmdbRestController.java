package com.ipl.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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

@RefreshScope
@RestController
public class OmdbRestController {
	
	@Autowired
	private FilmRepository fr;
	
	@Value("${omdb.max.retries}")
	private int maxRetries;
	
	@Value("${omdb.max.random.films}")
	private int maxRandomFilms;
	
	@Value("${omdb.min.random.films}")
	private int minRandomFilms;
	
	@Value("${omdb.imdb.id.prefix}")
	private String prefix;
	
	@Value("${omdb.max.random.id}")
	private int maxRandomId;
	
	@Value("${omdb.min.random.id}")
	private int minRandomId;
	
	@ApiOperation(value="Returns all information held for given film title. Returns 404 if no film is found.")
	@RequestMapping(method = RequestMethod.GET, path = "/film", produces = "application/json")
	public Film getFilm(@RequestParam("title") String filmTitle) throws FilmNotFoundException {
		List<Film> foundFilms = fr.search(filmTitle);
		if (foundFilms.isEmpty()) {
			throw new FilmNotFoundException(String.format("Could not find any film with title: %s", filmTitle));
		}
		return fr.searchById(foundFilms.get(0).getImdbID());
	}
	
	@ApiOperation(value="Returns an 'n' selection of randomly picked films")
	@RequestMapping(method = RequestMethod.GET, path = "/film/{n}", produces = "application/json")
	public List<Film> getRandomNFilms(@PathVariable int n) throws BadRequestException, FilmNotFoundException {
		if (n <= minRandomFilms || n > maxRandomFilms) {
			throw new BadRequestException(String.format("Path variable number must be a value of %d-%d inclusive.", minRandomFilms, maxRandomFilms)); 
		}
		
		List<Film> foundFilms = new ArrayList<>();
		int tries = 0;
		while (foundFilms.size() < n) {
			try {
				foundFilms.add(fr.searchById(getRandomId()));
			} catch (FilmNotFoundException e) {
				if(++tries >= maxRetries){
					throw new FilmNotFoundException("Failed to find enough random films.", e);
				}
			}
		}
		
		return foundFilms;
	}
	
	private String getRandomId() {
		int randomNum = ThreadLocalRandom.current().nextInt(minRandomId, maxRandomId);
		return prefix + String.format("%07d", randomNum);
	}
	

}
