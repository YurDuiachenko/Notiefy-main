package com.example.notiefy.web.rest;

import com.example.notiefy.service.MusicianService;
import com.example.notiefy.web.rest.model.MusicianModel;
import com.example.notiefy.web.rest.model.assembler.MusicianModelAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.UUID.fromString;

@RestController
@RequiredArgsConstructor
@RequestMapping("/musician")
public class MusicianController {
    private final MusicianModelAssembler musicianModelAssembler;
    private final MusicianService musicianService;

    @GetMapping("/{id}")
    public MusicianModel getMusicianById(@PathVariable("id") String musicianId) {
        return musicianModelAssembler.toModel(musicianService.getById(fromString(musicianId)));
    }
}