package com.bogdan.SpringMongoCRUD.controller;

import com.bogdan.SpringMongoCRUD.model.Movie;
import com.bogdan.SpringMongoCRUD.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/movie")
    public ResponseEntity<String> createMovie(@RequestBody Movie movie) {

        try {
            movieRepository.save(movie);
            return new ResponseEntity<>("Succesfully added movie " + movie.getTitle(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movie")
    public ResponseEntity getAllMovies() {

        List<Movie> movies = movieRepository.findAll();

        if(movies.size() > 0){

            return new ResponseEntity(movies, HttpStatus.OK);
        }else {
            return new ResponseEntity("No movies found", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/movie/{id}")
    public ResponseEntity deleteMovieById(@PathVariable("id") int id) {


        try {
            movieRepository.deleteById(id);
            return new ResponseEntity("Successfully deleted movie with id " + id, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value="/movie/{id}")
    public ResponseEntity updateById(@PathVariable("id") int id, @RequestBody Movie newMovie){

        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isPresent()) {

            Movie movieToSave = movieOptional.get();
            movieToSave.setTitle(newMovie.getTitle());
            movieToSave.setRating(newMovie.getRating());
            movieToSave.setGenre(newMovie.getGenre());
            movieRepository.save(movieToSave);
            return new ResponseEntity("Updated movie with id " + id, HttpStatus.OK);
        }else {
            return new ResponseEntity("The movie with id " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
