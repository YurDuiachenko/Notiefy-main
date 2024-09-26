package com.example.notiefy.web.graphql.model;

import lombok.*;

@Getter
@Setter
public class Song {
    private String id;
    private String name;
    private int duration;
    private int year;
    private String genre;
}
