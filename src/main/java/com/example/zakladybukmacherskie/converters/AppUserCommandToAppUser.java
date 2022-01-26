package com.example.zakladybukmacherskie.converters;

import com.example.zakladybukmacherskie.commands.AppUserCommand;
import com.example.zakladybukmacherskie.model.AppUser;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AppUserCommandToAppUser implements Converter<AppUserCommand, AppUser> {

    @Synchronized
    @Nullable
    @Override
    public AppUser convert(AppUserCommand source) {

        if(source == null){
            return null;
        }
        final AppUser appUser = new AppUser();
        appUser.setId(source.getId());
        appUser.setNick(source.getNick());
        appUser.setPin(source.getPin());
        appUser.setBets(source.getBets());
        appUser.setLoginTime(source.getLoginTime());
        return appUser;
    }
}
