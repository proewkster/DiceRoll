package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.Categorie;
import be.thomasmore.graduaten.diceroll.entity.Game;
import be.thomasmore.graduaten.diceroll.objects.Filter;
import be.thomasmore.graduaten.diceroll.service.CategorieService;
import be.thomasmore.graduaten.diceroll.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CategorieController {
    @Autowired
    CategorieService categorieService;
    @Autowired
    GameService gameService;
    @Autowired
    public CategorieController() {

    }
/*    @RequestMapping("/categorie")
    public String categorie(Model model){
        List<Categorie> categories = categorieService.getCategories();
        model.addAttribute("categories", categories);
        List<Game> games = gameService.getGames();
        model.addAttribute("games", games);
        return "categorie";}*/
    @RequestMapping(value = "/categorie", method = RequestMethod.GET)
    public ModelAndView GetCategorieGames(Filter filter){
        ModelAndView mav = new ModelAndView("categorie");
        List<Categorie> categories = categorieService.getCategories();
        List <Game> games = null;
        if (filter.getCategorieIds() !=null) {
            switch (filter.getCategorieIds().length) {
                case 0:
                    games = gameService.getGames();
                    break;
                case 1:
                    games = gameService.getFilterCategorie(filter.getCategorieIds()[0]);
                    break;
                case 2:
                    games = gameService.getFilter2Categories(filter.getCategorieIds()[0], filter.getCategorieIds()[1]);
                    break;
                default: games = gameService.getFilter2Categories(filter.getCategorieIds()[0], filter.getCategorieIds()[1]);
                    break;
            }
        }
        else{
            games =  gameService.getGames();
        }
        mav.addObject("categories", categories);
        mav.addObject("games", games);
        mav.addObject("filter", filter);
        return mav;
    }
    @RequestMapping(value = "/categorie",method = RequestMethod.POST)
    public String PostCategorie(Filter filter,@RequestParam Long id){

        return "categorie";
    }
}
