package com.example.notiefy.service.impl;

import com.example.notiefy.domain.Musician;
import com.example.notiefy.domain.Song;
import com.example.notiefy.repository.MusicianRepository;
import com.example.notiefy.repository.SongRepository;
import com.example.notiefy.service.SongService;
import com.example.notiefy.web.rabbitmq.SongAddedEventSupplier;
import com.example.notiefy.web.rabbitmq.SongPlayedEventSupplier;
import jakarta.persistence.EntityNotFoundException;
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
    private final MusicianRepository musicianRepository;
    private final SongPlayedEventSupplier songPlayedEventSupplier;
    private final SongAddedEventSupplier songAddedEventSupplier;

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
        var song = songRepository.getReferenceById(songId);
        songPlayedEventSupplier.supply(song);

        return song;
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
        Musician musician = musicianRepository.findById(song.getMusician().getId())
                .orElseThrow(() -> new EntityNotFoundException("Musician not found"));

        musician.addSong(song);

        var addedSong = songRepository.save(song);
        musicianRepository.save(musician);

        songAddedEventSupplier.supply(addedSong);

        return addedSong;
    }

    @Override
    public String changeName(UUID id, String name) {
        return songRepository.save(
                songRepository.findById(id)
                        .get()
                        .setName(name)
        ).getName();
    }

    @Override
    public Boolean deleteSong(UUID id) {
        try {
            songRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
