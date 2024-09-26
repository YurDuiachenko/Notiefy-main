package com.example.notiefy.config;

import com.example.notiefy.domain.Album;
import com.example.notiefy.domain.Musician;
import com.example.notiefy.domain.Song;
import com.example.notiefy.repository.AlbumRepository;
import com.example.notiefy.repository.MusicianRepository;
import com.example.notiefy.repository.SongRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    private final MusicianRepository musicianRepository;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;

    public DataInitializer(MusicianRepository musicianRepository, AlbumRepository albumRepository, SongRepository songRepository) {
        this.musicianRepository = musicianRepository;
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        initializeData();
    }

    @Transactional
    public void initializeData() {
        // Create a musician
        Musician musician = new Musician();
        musician.setNickName("The Rolling Stones");
        musician.setGroup(true);
        musician.setCreated(LocalDateTime.now());
        musician.setModified(LocalDateTime.now());
        musician = musicianRepository.save(musician);

        // Create an album
        Album album = new Album();
        album.setName("Let It Bleed");
        album.setStudio("Olympic Studios");
        album.setYear(1969);
        album.setCreated(LocalDateTime.now());
        album.setModified(LocalDateTime.now());
        album.setMusician(musician);
        album = albumRepository.save(album);

        // Create songs
        Song song1 = new Song();
        song1.setName("Gimme Shelter");
        song1.setDuration(270); // 4 minutes 30 seconds
        song1.setYear(1969);
        song1.setGenre("Rock");
        song1.setCreated(LocalDateTime.now());
        song1.setModified(LocalDateTime.now());
        song1.setMusician(musician);
        song1.setAlbum(album);

        Song song2 = new Song();
        song2.setName("You Can't Always Get What You Want");
        song2.setDuration(420); // 7 minutes
        song2.setYear(1969);
        song2.setGenre("Rock");
        song2.setCreated(LocalDateTime.now());
        song2.setModified(LocalDateTime.now());
        song2.setMusician(musician);
        song2.setAlbum(album);

        // Save all entities
        musician.setAlbums(new ArrayList<>());
        musician.getAlbums().add(album);
        album.setSongs(new ArrayList<>());
        album.getSongs().addAll(List.of(song1, song2));
        musician.setSingles(new ArrayList<>());
        musician.getSingles().addAll(List.of(song1, song2));
        songRepository.saveAll(List.of(song1, song2));

        System.out.println("Sample data initialized");
    }
}

