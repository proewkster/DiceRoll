package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.Game;
import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    GameService gameService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/contact")
    public String contact(){return "contact";}

    @RequestMapping("/overons")
    public String overons(){return "overons";}

    @RequestMapping("/categorie")
    public String categorie(Model model){
        List<Game> games = gameService.getGames();
        model.addAttribute("games", games);
        return "categorie";}

    @RequestMapping("/winkelmand")
    public String winkelmand(){return "winkelmand";}

}
