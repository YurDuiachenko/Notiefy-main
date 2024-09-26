package com.example.notiefy.service.impl;

import com.example.notiefy.domain.Album;
import com.example.notiefy.repository.AlbumRepository;
import com.example.notiefy.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Override
    public Album getById(UUID albumId) {
        return albumRepository.getReferenceById(albumId);
    }

    @Override
    public List<Album> getAlbumsByMusician(UUID musicianId) {
        return albumRepository.findAllByMusicianId(musicianId);
    }
}
