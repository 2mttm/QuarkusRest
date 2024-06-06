package com.cedacri.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieWithExternalDirector {
    private Long id;
    private String title;
    private Director director;
    private double rating;
}
