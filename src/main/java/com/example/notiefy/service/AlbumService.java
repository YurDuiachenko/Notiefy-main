package com.example.notiefy.service;

import com.example.notiefy.domain.Album;

import java.util.List;
import java.util.UUID;

public interface AlbumService {
    Album getById(UUID albumId);

    List<Album> getAlbumsByMusician(UUID musicianId);
}
