package com.example.notiefy.service;

import com.example.notiefy.domain.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SongService {
    /**
     * Получить все песни
     *
     * @return список песен
     */
    List<Song> getAll();

    /**
     * Найти все песни с заданным названием
     *
     * @param name название песни
     * @return список песен
     * // @throws UserNotFoundException пользователь не найден
     */
    List<Song> findAllByName(String name);

    /**
     * Найти все песни по страницам
     *
     * @param pageable страница
     * @return список песен
     * // @throws UserNotFoundException пользователь не найден
     */
    Page<Song> findAll(Pageable pageable);

    /**
     * Найти песню по идентификатору
     *
     * @param songId идентификатор песни
     * @return список песен
     * // @throws UserNotFoundException пользователь не найден
     */
    Song getById(UUID songId);

    /**
     * Найти все песни из альбома
     *
     * @param albumId название песни
     * @return список песен
     * // @throws UserNotFoundException пользователь не найден
     */
    List<Song> getSongsByAlbumId(UUID albumId);

    /**
     * Найти все синглы музыканта
     *
     * @param musicianId название песни
     * @return список песен
     * // @throws UserNotFoundException пользователь не найден
     */
    List<Song> getSinglesByMusician(UUID musicianId);

    /**
     * Удалить песню
     *
     * @param songId идентификатор песни
     */
    void deleteSongById(UUID songId);

    /**
     * Отфильтровать песни по имени
     *
     * @param name наименование песни
     */
    List<Song> filterSongsByName(String name);
}
