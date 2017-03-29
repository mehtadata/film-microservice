package com.ipl.model;

public class Film  {
	
	private String title;
	private String director;
	
	public Film(){}
	
	public Film( String title, String director){
		this.director = director;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	@Override
	public String toString(){
		return title + ": " + director;
	}
	

}
