package com.ipl.model;

import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Film {

	@JsonProperty("Title")
	private String title;

	@JsonProperty("Director")
	private String director;

	@JsonProperty("Released")
	private String released;

	@JsonProperty("Runtime")
	private String runtime;

	@JsonProperty("Genre")
	private String genre;

	@JsonProperty("Writer")
	private String writer;

	@JsonProperty("Actors")
	private String actors;

	@JsonProperty("Plot")
	private String plot;

	@JsonProperty("imdbID")
	private String imdbID;

	@JsonProperty("Type")
	private String type;

	@JsonProperty("Poster")
	private String poster;

	@JsonProperty("Year")
	private String year;

	@JsonProperty("Ratings")
	private List<Rating> ratings;
	
	@JsonProperty("Metascore")
	private String metascore;
	
	@JsonProperty("imdbRating")
	private String imdbRating;	
	
	@Transient
	@JsonProperty("Response")
	private String response;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public String getMetascore() {
		return metascore;
	}

	public void setMetascore(String metascore) {
		this.metascore = metascore;
	}

	public String getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(String imdbRating) {
		this.imdbRating = imdbRating;
	}
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return title + ": " + director;
	}

}
