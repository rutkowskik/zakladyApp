package com.example.zakladybukmacherskie.services;

import com.example.zakladybukmacherskie.model.Game;
import com.example.zakladybukmacherskie.repositories.GameRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @Override
    public Set<Game> getGames() {

        Set<Game> gameSet = new HashSet<>();
        gameRepository.findAll().iterator().forEachRemaining(gameSet::add);

        return gameSet;
    }

    @Override
    public Game findById(Long id) {

        Optional<Game> gameOptional = gameRepository.findById(id);

        if (!gameOptional.isPresent()){
            throw new RuntimeException("Nie znaleziono meczu");
        }
        return gameOptional.get();
    }
}
