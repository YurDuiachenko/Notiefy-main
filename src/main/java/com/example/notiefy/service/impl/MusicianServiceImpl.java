package com.example.notiefy.service.impl;

import com.example.notiefy.domain.Musician;
import com.example.notiefy.repository.MusicianRepository;
import com.example.notiefy.service.MusicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MusicianServiceImpl implements MusicianService {
    private final MusicianRepository musicianRepository;

    @Override
    public Musician getById(UUID musicianId) {
        return musicianRepository.getReferenceById(musicianId);
    }
}
