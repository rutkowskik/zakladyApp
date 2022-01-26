package com.example.zakladybukmacherskie.converters;

import com.example.zakladybukmacherskie.commands.AppUserCommand;
import com.example.zakladybukmacherskie.model.AppUser;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AppUserToAppUserCommand implements Converter<AppUser, AppUserCommand> {

    @Synchronized
    @Nullable
    @Override
    public AppUserCommand convert(AppUser source) {

        if(source == null){
            return null;
        }
        final AppUserCommand appUserCommand = new AppUserCommand();
        appUserCommand.setId(source.getId());
        appUserCommand.setNick(source.getNick());
        appUserCommand.setPin(source.getPin());
        appUserCommand.setBets(source.getBets());
        appUserCommand.setLoginTime(source.getLoginTime());
        return appUserCommand;
    }
}
