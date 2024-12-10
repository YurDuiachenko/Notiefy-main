package com.example.notiefy.web.rabbitmq;


import com.example.notiefy.domain.Song;
import com.example.notiefy.web.rabbitmq.model.AddedSong;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SongAddedEventSupplier {
    private final RabbitTemplate rabbitTemplate;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Value("${rabbitmq.song-added.exchange}")
    private String songAddedExchangeName;
    @Value("${rabbitmq.song-added.key}")
    private String songAddedKey;

    /**
     * Отправить информацию о добавленной песне
     *
     * @param song песня
     */
    public void supply(Song song) {
        var addedSong = modelMapper.map(song, AddedSong.class);
        addedSong.setMusicianNickName(song.getMusician().getNickName());
        try {
            var addedSongJson = objectMapper.writeValueAsString(addedSong);
            rabbitTemplate.convertAndSend(songAddedExchangeName, songAddedKey, addedSongJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}