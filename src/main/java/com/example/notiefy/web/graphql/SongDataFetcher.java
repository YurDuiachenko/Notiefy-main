package com.example.notiefy.web.graphql;

import com.example.notiefy.service.SongService;
import com.example.notiefy.web.graphql.model.Song;
import com.example.notiefy.web.graphql.model.SubmittedSong;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.List;
import java.util.UUID;

@DgsComponent
@RequiredArgsConstructor
public class SongDataFetcher {
    private final SongService songService;
    private final ModelMapper modelMapper;
    private final RabbitTemplate rabbitTemplate;

    @DgsQuery
    public List<Song> findSongsByName(@InputArgument String name) {
        return songService.filterSongsByName(name).stream()
            .map(song -> modelMapper.map(song, Song.class))
            .toList();
    }

    @DgsQuery
    public List<Song> findSongsByYear(@InputArgument int year) {
        return songService.filterSongsByYear(year).stream()
            .map(song -> modelMapper.map(song, Song.class))
            .toList();
    }

    @DgsQuery
    public List<Song> findAll() {
        return songService.getAll().stream()
            .map(song -> modelMapper.map(song, Song.class))
            .toList();
    }

    @DgsMutation
    public Song addSong(@InputArgument SubmittedSong song) {
        var addedSong = songService.addSong(modelMapper.map(song, com.example.notiefy.domain.Song.class));
        rabbitTemplate.convertAndSend("songAddedExchange", "song.added", addedSong);

        return modelMapper.map(addedSong, Song.class);
    }

    @DgsMutation
    public String changeSongName(@InputArgument String id, @InputArgument String name) {
        return songService.changeName(UUID.fromString(id), name);
    }

    @DgsMutation
    public Boolean deleteSong(@InputArgument String songId) {
        return songService.deleteSong(UUID.fromString(songId));
    }
}