package com.example.zakladybukmacherskie.services;

import com.example.zakladybukmacherskie.commands.BetCommand;
import com.example.zakladybukmacherskie.converters.BetCommandToBet;
import com.example.zakladybukmacherskie.converters.BetToBetCommand;
import com.example.zakladybukmacherskie.jdbc.QueryExecutor;
import com.example.zakladybukmacherskie.model.Bet;
import com.example.zakladybukmacherskie.repositories.BetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class BetServiceImpl implements BetService{

    private final BetRepository betRepository;
    private final BetCommandToBet betCommandToBet;
    private final BetToBetCommand betToBetCommand;

    public BetServiceImpl(BetRepository betRepository, BetCommandToBet betCommandToBet,
                          BetToBetCommand betToBetCommand) {
        this.betRepository = betRepository;
        this.betCommandToBet = betCommandToBet;
        this.betToBetCommand = betToBetCommand;
    }

    @Override
    @Transactional
    public BetCommand saveBetCommand(BetCommand betCommand) {

        betCommand.setBetTime(LocalDateTime.now());
        Bet detachedBet = betCommandToBet.convert(betCommand);
        Bet savedBet = betRepository.save(detachedBet);
        log.debug("Saved Bet " + savedBet.getId());
//        QueryExecutor.executeQuery("INSERT INTO bets VALUES( id='" + betCommand.getId() + "');");
        return betToBetCommand.convert(savedBet);
    }
}
