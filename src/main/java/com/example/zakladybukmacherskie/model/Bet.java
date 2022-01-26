package com.example.zakladybukmacherskie.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime playTime;
    private LocalDateTime betTime;
    private String score;
    @ManyToOne(cascade = CascadeType.ALL)
    private AppUser user;
    @ManyToOne
    private Game game;
}
