package com.example.zakladybukmacherskie.commands;

import com.example.zakladybukmacherskie.model.AppUser;
import com.example.zakladybukmacherskie.model.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BetCommand {

    private Long id;
    private LocalDateTime playTime;
    private LocalDateTime betTime;
    @Pattern(regexp ="(\\d+\\-\\d+)", message = "Proszę podać wynik w postaci (X-X)")
    @NotBlank(message = "Pole nie może być puste")
    private String score;
    private AppUser user;
    private Game game;
}
