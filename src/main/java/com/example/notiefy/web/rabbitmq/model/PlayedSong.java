package com.example.notiefy.web.rabbitmq.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PlayedSong implements Serializable {
    private String name;
    private String genre;
    private String musicianNickName;
    private LocalDateTime timePlayed;
}