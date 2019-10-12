package com.demo.microservices.movieinfoservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.movieinfoservice.model.Movie;
import com.demo.microservices.movieinfoservice.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieResource {

	@Autowired
	public MovieService abc;
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId){
		return abc.getMovieInfo(movieId);
	}
}
