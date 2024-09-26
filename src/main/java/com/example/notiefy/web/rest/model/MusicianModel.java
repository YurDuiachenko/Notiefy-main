package com.example.notiefy.web.rest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@Accessors(chain = true)
public class MusicianModel extends RepresentationModel<MusicianModel> {
    private String nickName;
    private boolean isGroup;
}
