package com.example.football.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stats")
public class Stat extends BaseEntity {

    @Column(nullable = false)
    private float shooting;

    @Column(nullable = false)
    private float passing;

    @Column(nullable = false)
    private float endurance;
}
