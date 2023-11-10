package com.gabrielliondas.workshopmongo.repository;

import com.gabrielliondas.workshopmongo.domain.Profilepicture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends MongoRepository<Profilepicture, String> {

}
