package com.example.notiefy.web.rest.model.assembler;

import com.example.notiefy.domain.Album;
import com.example.notiefy.web.rest.AlbumController;
import com.example.notiefy.web.rest.MusicianController;
import com.example.notiefy.web.rest.SongController;
import com.example.notiefy.web.rest.model.AlbumModel;
import lombok.NonNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Controller;

import static java.lang.String.valueOf;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
public class AlbumModelAssembler extends RepresentationModelAssemblerSupport<Album, AlbumModel> {

    public AlbumModelAssembler() {
        super(AlbumController.class, AlbumModel.class);
    }

    @Override
    @NonNull
    public AlbumModel toModel(@NonNull Album entity) {
        AlbumModel model = instantiateModel(entity);

        model.add(linkTo(methodOn(AlbumController.class)
                .getAlbumById(valueOf(entity.getId())))
                .withSelfRel())
            .add(linkTo(methodOn(SongController.class)
                .getSongsByAlbum(valueOf(entity.getId())))
                .withRel("songs")
                .withTitle("Получить песни альбома")
                .withType("GET"))
            .add(linkTo(methodOn(MusicianController.class)
                .getMusicianById(valueOf(entity.getMusician().getId())))
                .withRel("author")
                .withTitle("Получить информацию о музыканте")
                .withType("GET"));

        return model.setName(entity.getName())
            .setStudio(entity.getStudio())
            .setYear(entity.getYear());
    }
}
