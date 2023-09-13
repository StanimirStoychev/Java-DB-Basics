package com.example.football.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column
    private Position position;

    @ManyToOne
    private Town town;

    @ManyToOne
    private Team team;

    @OneToOne
    private Stat stat;

    @Override
    public String toString() {
        return String.format("Player - %s %s%n" +
                "   Position - %s%n" +
                "   Team - %s%n" +
                "   Stadium - %s%n",
                firstName, lastName, position.toString(), team.getName(), team.getStadiumName());
    }
}
