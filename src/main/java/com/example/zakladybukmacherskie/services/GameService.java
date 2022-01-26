package com.example.zakladybukmacherskie.services;

import com.example.zakladybukmacherskie.model.Game;

import java.util.Set;

public interface GameService {

    Set<Game> getGames();

    Game findById(Long id);
}
