package com.gabrielliondas.workshopmongo.repository;

import com.gabrielliondas.workshopmongo.domain.Post;
import com.gabrielliondas.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
