package com.example.notiefy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
 * Исполнитель
 */
@Entity
@Table(name = "musician")
@Getter
@Setter
@NoArgsConstructor
public class Musician extends BaseEntity {
    /*
     * Имя, под которым выступает
     */
    @Column(name = "nickName")
    private String nickName;
    /*
     * Является ли группой
     */
    @Column(name = "isGroup")
    private boolean isGroup;
    /*
     * Альбомы
     */
    @OneToMany(mappedBy = "musician", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Album> albums = new ArrayList<>();
    /*
     * Синглы
     */
    @OneToMany(mappedBy = "musician", cascade = CascadeType.REFRESH, orphanRemoval = true)
    private List<Song> singles = new ArrayList<>();
}
