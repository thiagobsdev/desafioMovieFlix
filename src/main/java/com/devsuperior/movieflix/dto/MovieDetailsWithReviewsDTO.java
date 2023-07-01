package com.devsuperior.movieflix.dto;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;

public class MovieDetailsWithReviewsDTO extends MovieDetailsDTO{
	
	List<ReviewDTO> reviewsDTO = new ArrayList<>();
	
	public MovieDetailsWithReviewsDTO() {
		super();
	}

	public MovieDetailsWithReviewsDTO(Movie movie ) {
		setId(movie.getId());
		setTitle(movie.getTitle());
		setSubTitle(movie.getSubTitle());
		setYear(movie.getYear());
		setImgUrl(movie.getImgUrl());
		setSynopsis(movie.getSynopsis());
		setGenre( new GenreDTO(movie.getGenre()));
		
		for(Review rev : movie.getReviews() ) {
			reviewsDTO.add(new ReviewDTO(rev));
		}
	}

	public List<ReviewDTO> getReviewsDTO() {
		return reviewsDTO;
	}
	
	
	
}
