package com.example.zakladybukmacherskie.controlres;

import com.example.zakladybukmacherskie.commands.AppUserCommand;
import com.example.zakladybukmacherskie.services.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Slf4j
@Controller
public class LoginController {

    private final AppUserService appUserService;

    public LoginController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    @RequestMapping("/")
    public String getLoginPage(Model model) {

        model.addAttribute("appuser", new AppUserCommand());
        return "login";
    }

    @PostMapping("/")
    public String saveUser(@Valid @ModelAttribute("appuser") AppUserCommand command,
                           BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError ->
                    log.debug(objectError.toString()));
            return "login";
        }else{
            command.setLoginTime(LocalDateTime.now());
            appUserService.saveAppUserCommand(command);

            return "redirect:/allgame";
        }
    }
}
