package com.cedacri.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movie {
    private Long id;
    private String title;
    private String director;
    private double rating;
}
