package com.example.notiefy.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/*
 * Базовая сущность
 */
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    /*
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    /*
     * Дата и время создания
     */
    @Column(name = "created")
    protected LocalDateTime created;
    /*
     * Дата и время изменения
     */
    @Column(name = "modified")
    protected LocalDateTime modified;
}
