package com.example.zakladybukmacherskie.repositories;

import com.example.zakladybukmacherskie.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    AppUser findByLoginTime(LocalDateTime time);
}
