package com.example.notiefy.repository;

import com.example.notiefy.domain.Musician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MusicianRepository extends JpaRepository<Musician, UUID> {
}
