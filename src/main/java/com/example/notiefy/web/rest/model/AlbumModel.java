package com.example.notiefy.web.rest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Accessors(chain = true)
public class AlbumModel extends RepresentationModel<AlbumModel> {
    private String name;
    private String studio;
    private int year;
}