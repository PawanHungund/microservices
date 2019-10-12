package com.demo.microservices.movieinfoservice.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.demo.microservices.movieinfoservice.model.Movie;

@Service
public class MovieServiceImpl implements MovieService {

private static List<Movie> movies = new ArrayList<>();
	
	static {
		movies.add(new Movie("1234","Transformers", "Action Movie"));
		movies.add(new Movie("5678","Interstellar", "Sci-Fi Movie"));
		movies.add(new Movie("2121","Hangover", "Comedy"));
		movies.add(new Movie("4536","Shawshank Redemption", "Drama/Mystery"));
	}
	
	@Override
	public Movie getMovieInfo(String movieId){
		return movies.stream().
		filter(movie -> movie.getMovieId().equals(movieId)).findFirst().orElse(null);
	}
}
