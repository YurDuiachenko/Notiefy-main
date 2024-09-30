package com.example.notiefy.service.impl;

import com.example.notiefy.domain.Song;
import com.example.notiefy.repository.SongRepository;
import com.example.notiefy.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    @Override
    public List<Song> getAll() {
        return songRepository.findAll();
    }

    @Override
    public List<Song> findAllByName(String name) {
        return null;
    }

    @Override
    public Page<Song> findAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public Song getById(UUID songId) {
        return songRepository.getReferenceById(songId);
    }

    @Override
    public List<Song> getSongsByAlbumId(UUID albumId) {
        return songRepository.findAllByAlbumId(albumId);
    }

    @Override
    public List<Song> getSinglesByMusician(UUID musicianId) {
        return songRepository.findAllByMusicianId(musicianId);
    }

    @Override
    public void deleteSongById(UUID songId) {

    }

    @Override
    public List<Song> filterSongsByName(String name) {
        return songRepository.findAll().stream()
            .filter(song -> song.getName().contains(name))
            .toList();
    }

    @Override
    public List<Song> filterSongsByYear(int year) {
        return songRepository.findAll().stream()
            .filter(song -> song.getYear() == year)
            .toList();
    }

    @Override
    public Song addSong(Song song) {
        return songRepository.save(song);
    }
}
