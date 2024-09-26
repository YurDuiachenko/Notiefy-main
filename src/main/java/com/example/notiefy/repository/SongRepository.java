package com.example.notiefy.repository;

import com.example.notiefy.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SongRepository extends JpaRepository<Song, UUID> {
    List<Song> findAllByAlbumId(UUID album_id);

    List<Song> findAllByMusicianId(UUID musician_id);
}
