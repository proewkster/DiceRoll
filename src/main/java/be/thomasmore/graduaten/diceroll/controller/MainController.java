package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.Categorie;
import be.thomasmore.graduaten.diceroll.entity.Game;
import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.service.CategorieService;
import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import be.thomasmore.graduaten.diceroll.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    GameService gameService;
    @Autowired
    CategorieService categorieService;

    @RequestMapping("/")
    public String index(Model model) {
        List<Game> games = gameService.getHighestRated();
        model.addAttribute("games", games);
        return "index";
    }

    @RequestMapping("/contact")
    public String contact(){return "contact";}

    @RequestMapping("/overons")
    public String overons(){return "overons";}

    @RequestMapping("/categorie")
    public String categorie(Model model){
        List<Categorie> categories = categorieService.getCategories();
        model.addAttribute("categories", categories);
        List<Game> games = gameService.getGames();
        model.addAttribute("games", games);
        return "categorie";}

    @RequestMapping("/winkelmand")
    public String winkelmand(){return "winkelmand";}

    @GetMapping("/newhome")
    public ModelAndView newHome() {
        ModelAndView mv = new ModelAndView("newHome");

        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        return mv;
    }
}
