package com.demo.microservices.movieinfoservice.service;

import com.demo.microservices.movieinfoservice.model.Movie;

public interface MovieService {
	
	public Movie getMovieInfo(String movieId);
}
