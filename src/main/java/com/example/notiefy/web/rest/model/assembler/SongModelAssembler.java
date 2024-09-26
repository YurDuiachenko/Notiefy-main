package com.example.notiefy.web.rest.model.assembler;

import com.example.notiefy.domain.Song;
import com.example.notiefy.web.rest.AlbumController;
import com.example.notiefy.web.rest.MusicianController;
import com.example.notiefy.web.rest.SongController;
import com.example.notiefy.web.rest.model.SongModel;
import lombok.NonNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static java.lang.String.valueOf;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SongModelAssembler extends RepresentationModelAssemblerSupport<Song, SongModel> {

    public SongModelAssembler() {
        super(SongController.class, SongModel.class);
    }

    @Override
    @NonNull
    public SongModel toModel(@NonNull Song entity) {
        SongModel model = instantiateModel(entity);

        model.add(linkTo(methodOn(SongController.class)
                .getSongById(valueOf(entity.getId())))
                .withSelfRel())
            .add(linkTo(methodOn(AlbumController.class)
                .getAlbumById(valueOf(entity.getAlbum().getId())))
                .withRel("album")
                .withTitle("Получить информацию об альбоме")
                .withType("GET"))
            .add(linkTo(methodOn(MusicianController.class)
                .getMusicianById(valueOf(entity.getMusician().getId())))
                .withRel("musician")
                .withTitle("Получить информацию о музыканте")
                .withType("GET"));

        return model.setName(entity.getName())
            .setDuration(entity.getDuration())
            .setGenre(entity.getGenre())
            .setYear(entity.getYear());
    }
}
