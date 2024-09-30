package com.example.notiefy.web.graphql.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubmittedSong {
    private String id;
    private String name;
    private int duration;
    private int year;
    private String genre;
    private String albumId;
    private Musician musician;
}
