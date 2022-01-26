package com.example.zakladybukmacherskie.services;

import com.example.zakladybukmacherskie.commands.BetCommand;

public interface BetService {

    BetCommand saveBetCommand(BetCommand betCommand);
}
