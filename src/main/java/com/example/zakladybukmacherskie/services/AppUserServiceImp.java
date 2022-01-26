package com.example.zakladybukmacherskie.services;

import com.example.zakladybukmacherskie.commands.AppUserCommand;
import com.example.zakladybukmacherskie.converters.AppUserCommandToAppUser;
import com.example.zakladybukmacherskie.converters.AppUserToAppUserCommand;
import com.example.zakladybukmacherskie.model.AppUser;
import com.example.zakladybukmacherskie.repositories.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AppUserServiceImp implements AppUserService{

    private final AppUserCommandToAppUser appUserCommandToAppUser;
    private final AppUserToAppUserCommand appUserToAppUserCommand;
    private final AppUserRepository appUserRepository;

    public AppUserServiceImp(AppUserCommandToAppUser appUserCommandToAppUser,
                             AppUserToAppUserCommand appUserToAppUserCommand,
                             AppUserRepository appUserRepository) {
        this.appUserCommandToAppUser = appUserCommandToAppUser;
        this.appUserToAppUserCommand = appUserToAppUserCommand;
        this.appUserRepository = appUserRepository;
    }

    @Override
    @Transactional
    public AppUserCommand saveAppUserCommand(AppUserCommand command) {

        AppUser detachedUser = appUserCommandToAppUser.convert(command);
        AppUser savedUser = appUserRepository.save(detachedUser);
        log.debug("Saved user " + savedUser.getNick());

        return appUserToAppUserCommand.convert(savedUser);
    }

    @Override
    @Transactional
    public AppUser findByLastLoginTime() {

        List<AppUser> users = new ArrayList<>();
        appUserRepository.findAll().forEach(users::add);

        List<LocalDateTime> timeList = users.stream().map(AppUser::getLoginTime)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        Optional<LocalDateTime> lastUserLoginTimeOptional = timeList.stream().findFirst();

        if (!(lastUserLoginTimeOptional.isPresent())){
            throw new RuntimeException("Użytkownik nie zalogowany!");
        }
        return appUserRepository.findByLoginTime(lastUserLoginTimeOptional.get());
    }
}
