package com.example.zakladybukmacherskie.commands;

import com.example.zakladybukmacherskie.model.Bet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AppUserCommand {

    private Long id;
    @NotBlank(message = "Nick nie może byc pusty")
    @Size(min = 3,message = "Musi zawierać conajmiej 3 znaki")
    private String nick;
    @NotNull(message = "Pin nie może być pusty")
    @Min(999)
    private Integer pin;
    private List<Bet> bets = new ArrayList<>();
    private LocalDateTime loginTime;
}
