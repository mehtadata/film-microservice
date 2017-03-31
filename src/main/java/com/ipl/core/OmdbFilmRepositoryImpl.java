package com.ipl.core;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ipl.core.exceptions.FilmNotFoundException;
import com.ipl.model.Film;

public class OmdbFilmRepositoryImpl implements FilmRepository {
	
	private String searchURL;
	
	public OmdbFilmRepositoryImpl(String searchUrl){
		this.searchURL = searchUrl;
	}

	@Override
	public List<Film> search(String title) throws FilmNotFoundException {
		RestTemplate rt = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(searchURL)
				.queryParam("s", title)
				.queryParam("type", "movie");
		
		try {
			String resultingJson = rt.getForObject(builder.build().encode().toUri(), String.class);
			return new ObjectMapper().readValue(
					new JSONObject(resultingJson).getJSONArray("Search").toString(), 
					new TypeReference<List<Film>>() {});
		} catch (RestClientException | JSONException | IOException e) {
			throw new FilmNotFoundException(e);
		}
	}

	@Override
	public Film searchById(String id) throws FilmNotFoundException {
		RestTemplate rt = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(searchURL)
				.queryParam("i", id)
				.queryParam("type", "movie");
		
		try {
			Film foundFilm = rt.getForObject(builder.build().encode().toUri(), Film.class);
			if (foundFilm.getResponse() == null || "False".equals(foundFilm.getResponse())){
				throw new FilmNotFoundException("No response for this id");
			}
			return foundFilm;
		} catch (RestClientException e) {
			throw new FilmNotFoundException(e);
		}
	}

}
