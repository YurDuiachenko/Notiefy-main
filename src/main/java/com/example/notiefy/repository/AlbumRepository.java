package com.example.notiefy.repository;

import com.example.notiefy.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlbumRepository  extends JpaRepository<Album, UUID> {
    List<Album> findAllByMusicianId(UUID musician_id);
}
