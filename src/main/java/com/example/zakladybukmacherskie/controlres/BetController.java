package com.example.zakladybukmacherskie.controlres;

import com.example.zakladybukmacherskie.commands.BetCommand;
import com.example.zakladybukmacherskie.model.AppUser;
import com.example.zakladybukmacherskie.model.Bet;
import com.example.zakladybukmacherskie.model.Game;
import com.example.zakladybukmacherskie.services.AppUserService;
import com.example.zakladybukmacherskie.services.BetService;
import com.example.zakladybukmacherskie.services.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class BetController {

    private final GameService gameService;
    private final BetService betService;
    private final AppUserService appUserService;

    public BetController(GameService gameService, BetService betService,
                         AppUserService appUserService){
        this.gameService = gameService;
        this.betService = betService;
        this.appUserService=appUserService;
    }

    @GetMapping
    @RequestMapping("/game/{gameId}/bets")
    public String listBets(@PathVariable String gameId, Model model){

        model.addAttribute("game", gameService.findById(Long.valueOf(gameId)));

        return "bets";
    }

    @GetMapping
    @RequestMapping("/game/{gameId}/bet/new")
    public String addNewBet(@PathVariable String gameId, Model model){

        model.addAttribute("game", gameService.findById(Long.valueOf(gameId)));
        model.addAttribute("bet", new BetCommand());

        return "betForm";
    }

    @GetMapping
    @RequestMapping("/game/{gameId}/bets/error")
    public String listBetsError(@PathVariable String gameId, Model model){

        model.addAttribute("game", gameService.findById(Long.valueOf(gameId)));

        return "betsError";
    }

    @PostMapping("/game/{gameId}/bets")
    public String saveNewBet(@PathVariable String gameId,@Valid @ModelAttribute("bet") BetCommand betCommand,
                             BindingResult bindingResult, Model model) {
        // TODO: 24.01.2022 try put this into other method
        // TODO: 24.01.2022 implement JDBC as DB
        // TODO: 26.01.2022 sprawdznie czy podana propozycja zakladu juz istnieje
            try {
                model.addAttribute("game", gameService.findById(Long.valueOf(gameId)));//dodaje model gry any poprawnie zaladowac formularz przy wystapiniu bledu w wyniku
                Game game = gameService.findById(Long.valueOf(gameId));
                AppUser appUser = appUserService.findByLastLoginTime();

                boolean isGameLive = game.getDateOfGame().isAfter(LocalDateTime.now());
                if (!isGameLive) {
                    return "redirect:/allgame/err";
                }
                List<Bet> bets = new ArrayList<>(game.getBets());//dla aktualnej gry tworze list zakladow

                Map<AppUser, Long> appUserMapWithNumbersOfBets = bets.stream()
                        .collect(Collectors.groupingBy(Bet::getUser, Collectors.counting()));//tworze mape wszyskich uzykownikow wraz z iloscia ich wystapien

                Long numberOfBets = appUserMapWithNumbersOfBets.get(appUser);//pobieram ilosc wystapien uzytkownika, ktory dodaje zaklda

                if (!(numberOfBets == null || numberOfBets <= 2)) {
                    throw new RuntimeException("Jeden użytkowni może złożyć maksymalnie 3 zakłady");
                }
                if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(objectError ->
                        log.debug(objectError.toString()));
                return "betForm";
                }
                betCommand.setUser(appUser);
                betCommand.setGame(game);
                betCommand.setPlayTime(game.getDateOfGame());
                betService.saveBetCommand(betCommand);

                return "redirect:/game/" + Long.valueOf(gameId) + "/bets";
            }catch (RuntimeException e) {
                return "redirect:/game/" + Long.valueOf(gameId) + "/bets/error";
            }
    }
}

