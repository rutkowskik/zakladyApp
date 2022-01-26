package com.example.zakladybukmacherskie.controlres;

import com.example.zakladybukmacherskie.services.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping("/allgame")
    public String getGamesPage(Model model){

        model.addAttribute("games", gameService.getGames());

        return "games";
    }

    @RequestMapping("/allgame/err")
    public String getGamesPagePassed(Model model){

        model.addAttribute("games", gameService.getGames());

        return "gamesErr";
    }
}
