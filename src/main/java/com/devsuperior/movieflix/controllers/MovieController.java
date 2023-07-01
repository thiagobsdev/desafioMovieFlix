package com.devsuperior.movieflix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@PreAuthorize("hasAnyRole('ROLE_VISITOR','ROLE_MEMBER')")
	@GetMapping("/{id}")
	public MovieDetailsDTO findById(@PathVariable Long id) {
		MovieDetailsDTO movieDetailsDTO = movieService.findById(id);
		return movieDetailsDTO;
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_VISITOR','ROLE_MEMBER')")
	@GetMapping
	public ResponseEntity<Page<MovieCardDTO>> findByGenre(
			@RequestParam(name = "genreId", defaultValue = "") String id, Pageable page ) {
		Page< MovieCardDTO> result = movieService.findByGenre(id, page);
		return ResponseEntity.ok(result);
		
 		
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_VISITOR','ROLE_MEMBER')")
	@GetMapping("/{id}/reviews")
	public ResponseEntity<MovieDetailsDTO> findAndReviewByID(@PathVariable Long id) {
		MovieDetailsDTO result = movieService.findAndReviewByID(id);
		return ResponseEntity.ok(result);
		
 		
		
	}
	
	
}
