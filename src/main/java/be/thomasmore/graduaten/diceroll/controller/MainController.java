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
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");

        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        List<Game> games = gameService.getHighestRated();
        mv.addObject("games", games);

        return mv;
    }

    @GetMapping("/contact")
    public ModelAndView contact() {
        ModelAndView mv = new ModelAndView("contact");

        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        return mv;
    }

    @GetMapping("/overons")
    public ModelAndView overons() {
        ModelAndView mv = new ModelAndView("overons");

        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        return mv;
    }

    @RequestMapping("/categorie")
    public String categorie(Model model){
        List<Categorie> categories = categorieService.getCategories();
        model.addAttribute("categories", categories);
        List<Game> games = gameService.getGames();
        model.addAttribute("games", games);
        return "categorie";
    }

    @GetMapping("/winkelmand")
    public ModelAndView winkelmand() {
        ModelAndView mv = new ModelAndView("winkelmand");

        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        return mv;
    }

    @GetMapping("/newhome")
    public ModelAndView newHome() {
        ModelAndView mv = new ModelAndView("newHome");

        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        return mv;
    }
}
