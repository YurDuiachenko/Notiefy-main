package com.example.notiefy.web.rabbitmq;

import com.example.notiefy.domain.Song;
import com.example.notiefy.web.rabbitmq.model.PlayedSong;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SongPlayedEventSupplier {
    private final RabbitTemplate rabbitTemplate;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Value("${rabbitmq.song-played.exchange}")
    private String songPlayedExchangeName;
    @Value("${rabbitmq.song-played.key}")
    private String songPlayedKey;

    /**
     * Отправить информацию о прослушанной песне
     *
     * @param song песня
     */
    public void supply(Song song) {
        var playedSong = modelMapper.map(song, PlayedSong.class);
        playedSong.setTimePlayed(LocalDateTime.now());
        try {
            rabbitTemplate.convertAndSend(songPlayedExchangeName, songPlayedKey, objectMapper.writeValueAsString(playedSong));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
