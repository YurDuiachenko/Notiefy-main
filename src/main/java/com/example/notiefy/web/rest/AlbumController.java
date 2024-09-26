package com.example.notiefy.web.rest;

import com.example.notiefy.service.AlbumService;
import com.example.notiefy.web.rest.model.AlbumModel;
import com.example.notiefy.web.rest.model.assembler.AlbumModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.UUID.fromString;

@RestController
@RequiredArgsConstructor
@RequestMapping("album")
public class AlbumController {
    private final AlbumModelAssembler albumModelAssembler;
    private final AlbumService albumService;

    @GetMapping("/{id}")
    public AlbumModel getAlbumById(@PathVariable("id") String albumId) {
        return albumModelAssembler.toModel(albumService.getById(fromString(albumId)));
    }

    @GetMapping("/musician/{id}")
    public AlbumModel getAlbumByMusician(@PathVariable("id") String musicianId) {
        return albumModelAssembler.toModel(albumService.getById(fromString(musicianId)));
    }
}