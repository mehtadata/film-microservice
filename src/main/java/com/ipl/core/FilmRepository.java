package com.ipl.core;

import java.util.List;

import com.ipl.core.exceptions.FilmNotFoundException;
import com.ipl.model.Film;

public interface FilmRepository {
	
	List<Film> search(String title) throws FilmNotFoundException;
	
	Film searchById(String id) throws FilmNotFoundException;

}
