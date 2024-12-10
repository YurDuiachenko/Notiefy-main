package com.example.notiefy.web.rabbitmq.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddedSong implements Serializable {
    private String name;
    private String musicianNickName;
}