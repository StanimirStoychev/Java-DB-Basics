package com.example.football.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player extends BaseEntity {

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthDate;

    private Position position;

    @ManyToOne
    private Town town;

    @ManyToOne
    private Team team;

    @OneToOne
    private Stat stat;
}
