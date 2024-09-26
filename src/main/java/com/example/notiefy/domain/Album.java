package com.example.notiefy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
 * Альбом
 */
@Entity
@Table(name = "album")
@Getter
@Setter
@NoArgsConstructor
public class Album extends BaseEntity {
    /*
     * Наименование
     */
    @Column(name = "name", nullable = false)
    private String name;
    /*
     * Студия записи
     */
    @Column(name = "studio")
    private String studio;
    /*
     * Год выпуска
     */
    @Column(name = "year")
    private int year;
    /*
     * Песни альбома
     */
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs = new ArrayList<>();
    /*
     * Песни альбома
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "musician_id")
    private Musician musician;
}