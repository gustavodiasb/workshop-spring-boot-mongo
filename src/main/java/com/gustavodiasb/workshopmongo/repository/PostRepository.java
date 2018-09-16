package com.gustavodiasb.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gustavodiasb.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
	
	
	/*
	Agora é possível utilizar o nome que desejar com as sintaxes Strings
	com a personalização abaixo.
	Um exemplo é a sintaxe 'findByTitle' por 'searchTitle'
	 */
	@Query("{ 'title': {$regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
}
