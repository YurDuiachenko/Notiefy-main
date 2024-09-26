package com.example.notiefy.service;

import com.example.notiefy.domain.Musician;

import java.util.UUID;

public interface MusicianService {
    Musician getById(UUID musicianId);
}
