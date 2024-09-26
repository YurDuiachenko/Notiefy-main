package com.example.notiefy.web.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.example.notiefy.web.graphql.model.Song;
import com.example.notiefy.service.SongService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
@RequiredArgsConstructor
public class SongResolver {
    private final SongService songService;
    private final ModelMapper modelMapper;

    @DgsQuery
    public List<Song> filterSongByName(@InputArgument String name) {
        return songService.filterSongsByName(name).stream()
            .map(song -> modelMapper.map(song, Song.class))
            .toList();
    }
}
