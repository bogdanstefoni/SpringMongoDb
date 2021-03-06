package com.bogdan.SpringMongoCRUD.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movie")
public class Movie {

    @Id
    private int id;

    private String title;
    private float rating;
    private String genre;
}
