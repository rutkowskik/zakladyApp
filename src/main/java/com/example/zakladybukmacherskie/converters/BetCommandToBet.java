package com.example.zakladybukmacherskie.converters;

import com.example.zakladybukmacherskie.commands.BetCommand;
import com.example.zakladybukmacherskie.model.Bet;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BetCommandToBet implements Converter<BetCommand, Bet> {

    @Synchronized
    @Nullable
    @Override
    public Bet convert(BetCommand source) {

        if (source == null){
            return null;
        }
        final Bet bet = new Bet();
        bet.setId(source.getId());
        bet.setPlayTime(source.getPlayTime());
        bet.setGame(source.getGame());
        bet.setScore(source.getScore());
        bet.setUser(source.getUser());
        bet.setBetTime(source.getBetTime());
        return bet;
    }
}
