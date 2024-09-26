package com.example.notiefy.domain;

import jakarta.persistence.*;
import lombok.*;

/*
 * Песня
 */
@Entity
@Table(name = "song")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Song extends BaseEntity{
    /*
     * Наименование
     */
    @Column(name = "name", nullable = false)
    private String name;
    /*
     * Продолжительность в секундах
     */
    @Column(name = "duration", nullable = false)
    private int duration;
    /*
     * Год выпуска
     */
    @Column(name = "year")
    private int year;
    /*
     * Жанр
     */
    @Column(name = "genre")
    private String genre;
    /*
     * Исполнитель
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "musician_id")
    private Musician musician;
    /*
     * Альбом, в который входит песня
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    private Album album;
}
