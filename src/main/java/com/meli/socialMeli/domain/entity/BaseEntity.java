package com.meli.socialMeli.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "DATE")
    private LocalDate createdAt;

    @Column(columnDefinition = "DATE")
    private LocalDate updatedAt;

}
