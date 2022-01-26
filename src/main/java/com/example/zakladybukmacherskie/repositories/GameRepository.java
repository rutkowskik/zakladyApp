package com.example.zakladybukmacherskie.repositories;

import com.example.zakladybukmacherskie.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

}
