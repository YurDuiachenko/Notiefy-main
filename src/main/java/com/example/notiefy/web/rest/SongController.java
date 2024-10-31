package com.example.notiefy.web.rest;

import com.example.notiefy.domain.Song;
import com.example.notiefy.service.SongService;
import com.example.notiefy.web.rest.model.SongModel;
import com.example.notiefy.web.rest.model.assembler.SongModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.UUID.fromString;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/song")
public class SongController {
    private final SongService songService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PagedResourcesAssembler<Song> pagedResourcesAssembler;
    private final SongModelAssembler songModelAssembler;

    @GetMapping("/all")
    public PagedModel<SongModel> getAll(Pageable pageable) {
        return pagedResourcesAssembler.toModel(songService.findAll(pageable), songModelAssembler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongModel> getSongById(@PathVariable("id") String songId) {


        return new ResponseEntity<>(songModelAssembler.toModel(songService.getById(fromString(songId))), OK);
    }

    @GetMapping("/album/{id}")
    public CollectionModel<SongModel> getSongsByAlbum(@PathVariable("id") String albumId) {
        return songModelAssembler.toCollectionModel(songService.getSongsByAlbumId(fromString(albumId)));
    }

    @GetMapping("/musician/{id}")
    public CollectionModel<SongModel> getSinglesByMusicianId(@PathVariable("id") String musicianId) {
        return songModelAssembler.toCollectionModel(songService.getSinglesByMusician(fromString(musicianId)));
    }

    @DeleteMapping("/{id}/delete")
    public void deleteSongById(@PathVariable("id") String songId) {
        songService.getById(fromString(songId));
    }
}
