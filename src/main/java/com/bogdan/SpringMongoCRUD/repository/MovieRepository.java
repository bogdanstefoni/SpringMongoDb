package com.bogdan.SpringMongoCRUD.repository;

import com.bogdan.SpringMongoCRUD.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository  extends MongoRepository<Movie, Integer> {
}
