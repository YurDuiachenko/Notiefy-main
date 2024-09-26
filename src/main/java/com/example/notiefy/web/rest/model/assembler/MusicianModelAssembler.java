package com.example.notiefy.web.rest.model.assembler;

import com.example.notiefy.domain.Musician;
import com.example.notiefy.web.rest.AlbumController;
import com.example.notiefy.web.rest.MusicianController;
import com.example.notiefy.web.rest.SongController;
import com.example.notiefy.web.rest.model.MusicianModel;
import lombok.NonNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static java.lang.String.valueOf;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MusicianModelAssembler extends RepresentationModelAssemblerSupport<Musician, MusicianModel> {
    public MusicianModelAssembler() {
        super(MusicianController.class, MusicianModel.class);
    }

    @Override
    @NonNull
    public MusicianModel toModel(@NonNull Musician entity) {
        MusicianModel model = instantiateModel(entity);

        model.add(linkTo(methodOn(MusicianController.class)
                .getMusicianById(valueOf(entity.getId())))
                .withSelfRel())
            .add(linkTo(methodOn(AlbumController.class)
                .getAlbumByMusician(valueOf(entity.getId())))
                .withRel("albums")
                .withTitle("Получить альбомы музыканта")
                .withType("GET"))
            .add(linkTo(methodOn(SongController.class)
                .getSinglesByMusicianId(valueOf(entity.getId())))
                .withRel("singles")
                .withTitle("Получить синглы музыканта")
                .withType("GET"));

        return model.setNickName(entity.getNickName())
            .setGroup(entity.isGroup());
    }
}
