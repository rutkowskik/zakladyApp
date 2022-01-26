package com.example.zakladybukmacherskie.services;

import com.example.zakladybukmacherskie.commands.AppUserCommand;
import com.example.zakladybukmacherskie.model.AppUser;

public interface AppUserService {

    AppUserCommand saveAppUserCommand(AppUserCommand command);

    AppUser findByLastLoginTime();
}
