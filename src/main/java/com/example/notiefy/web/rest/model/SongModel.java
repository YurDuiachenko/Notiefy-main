package com.example.notiefy.web.rest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Accessors(chain = true)
public class SongModel extends RepresentationModel<SongModel> {
    private String name;
    private int duration;
    private int year;
    private String genre;
}
