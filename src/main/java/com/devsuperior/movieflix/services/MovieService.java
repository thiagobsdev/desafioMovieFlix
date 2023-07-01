package com.devsuperior.movieflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.MovieDetailsWithReviewsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService  {
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Transactional(readOnly = true)
	public MovieDetailsDTO findById(Long id) {
		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado"));
		
		return new MovieDetailsDTO(movie);
	}
	
	@Transactional(readOnly = true)
	public Page<MovieCardDTO> findByGenre(String id, Pageable page) {
		
		Long idParsed = null;
		if(id.isBlank() || id.isEmpty()) {
			idParsed = null;
		}else {
			idParsed = Long.parseLong(id);
		}
		Page<Movie> pageMovie = movieRepository.findByGenre(idParsed, page);
		return pageMovie.map(x -> new MovieCardDTO(x));
	}
	
	@Transactional(readOnly = true)
	public MovieDetailsWithReviewsDTO findAndReviewByID(Long id) {
		Movie movie = movieRepository.getReferenceById(id);
		List <Review> reviews = reviewRepository.findByMovie(movie);
		movie.getReviews().addAll(reviews);
		return new MovieDetailsWithReviewsDTO(movie);
	}
	
	
	

	
}
