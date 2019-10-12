package com.demo.microservices.moviecatalogservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.demo.microservices.moviecatalogservice.modules.CatalogItem;
import com.demo.microservices.moviecatalogservice.modules.Movie;
import com.demo.microservices.moviecatalogservice.modules.Rating;
import com.demo.microservices.moviecatalogservice.modules.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder; 
	  
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
	
		// get all movie ids
		// For each movie Id, call movie info service and get details
		// put them all together.
		
		//UserRating userRating = restTemplate.getForObject("http://movie-ratings-service/ratingsdata/users/"+userId, UserRating.class);
		UserRating userRating = webClientBuilder.build().get().uri("http://movie-ratings-service/ratingsdata/users/"+userId).retrieve().bodyToMono(UserRating.class).block();
		List<Rating> ratings = userRating.getUserRating();
		
		return ratings.stream().map(rating -> {
			Movie movie = webClientBuilder.build().get().uri("http://movie-info-service/movies/"+ rating.getMovieId()).retrieve().bodyToMono(Movie.class).block();
			//Movie movie =  restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
		}).collect(Collectors.toList());
		
		
	}
}
