package com.ipl.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ipl.core.FilmRepository;
import com.ipl.core.OmdbFilmRepositoryImpl;

@Configuration
public class OmdbConfiguration {
	
	@Value("${omdb.search.url}")
	private String searchURL;
	
	public @Bean FilmRepository filmRepository() {
		return new OmdbFilmRepositoryImpl(searchURL);
	}

}
