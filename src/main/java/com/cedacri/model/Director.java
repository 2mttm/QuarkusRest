package com.cedacri.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Director {
    private String name;
    private int age;
    private int numberOfMovies;
}
