package com.devsuperior.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	UserService userService;

	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		
		UserDTO user = userService.getProfile();
		Review review = new Review();
		
		
		copyReviewDTOToReviewEntity(dto, review, user);
		return new ReviewDTO(reviewRepository.save(review), user);
	}

	private void copyReviewDTOToReviewEntity(ReviewDTO reviewDTO, Review review, UserDTO user) {
		
		review.setText(reviewDTO.getText());
		review.setUser(userRepository.getReferenceById(user.getId()));
		review.setMovie(movieRepository.getReferenceById(reviewDTO.getMovieId()));
	}

}
