package com.example.zakladybukmacherskie.repositories;

import com.example.zakladybukmacherskie.model.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BetRepository extends CrudRepository<Bet, Long> {

    Optional<List<Bet>> findBetByGameId(Long gameId);
}
