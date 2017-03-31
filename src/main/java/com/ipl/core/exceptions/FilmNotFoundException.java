package com.ipl.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class FilmNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilmNotFoundException(Exception e){
		super(e);
	}
	
	public FilmNotFoundException(String msg){
		super(msg);
	}
	
	public FilmNotFoundException(String msg, Exception e){
		super(msg, e);
	}

}
