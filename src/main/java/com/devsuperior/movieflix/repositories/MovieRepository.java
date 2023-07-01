package com.devsuperior.movieflix.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	Optional<Movie> findById(Long Id);

	@Query(nativeQuery = true, value = """
			SELECT *
			FROM TB_MOVIE
			WHERE (:id IS NULL OR TB_MOVIE.GENRE_ID = :id)
			GROUP BY TB_MOVIE.ID
			ORDER BY TB_MOVIE.TITLE
			""", countQuery = """
				SELECT count(*)
				FROM TB_MOVIE
				WHERE TB_MOVIE.GENRE_ID = :id
				GROUP BY TB_MOVIE.ID
				ORDER BY TB_MOVIE.TITLE
			""")
	Page<Movie> findByGenre(Long id, Pageable page);
	


}
