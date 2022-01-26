package com.example.zakladybukmacherskie.converters;

import com.example.zakladybukmacherskie.commands.BetCommand;
import com.example.zakladybukmacherskie.model.Bet;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BetToBetCommand implements Converter<Bet, BetCommand> {

    @Synchronized
    @Nullable
    @Override
    public BetCommand convert(Bet source) {

        if(source == null){
            return null;
        }
        final BetCommand betCommand = new BetCommand();
        betCommand.setId(source.getId());
        betCommand.setPlayTime(source.getPlayTime());
        betCommand.setGame(source.getGame());
        betCommand.setScore(source.getScore());
        betCommand.setUser(source.getUser());
        betCommand.setBetTime(source.getBetTime());
        return betCommand;
    }
}
