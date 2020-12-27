package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.Categorie;
import be.thomasmore.graduaten.diceroll.entity.Game;
import be.thomasmore.graduaten.diceroll.entity.PageInfo;
import be.thomasmore.graduaten.diceroll.objects.Filter;
import be.thomasmore.graduaten.diceroll.service.CategorieService;
import be.thomasmore.graduaten.diceroll.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView GetCategorieGames(@ModelAttribute("filter")Filter filter, @ModelAttribute("pageinfo") PageInfo pageinfo){
        ModelAndView mav = new ModelAndView("categorie");
        List<Categorie> categories = categorieService.getCategories();

        List <Game> games = null;
        if (filter.getCategorieIds() !=null) {
            switch (filter.getCategorieIds().length) {
                case 0:
                    pageinfo = gameService.getGames(pageinfo.currentPage, 20);
                    games = pageinfo.gamePage;
                    break;
                case 1:
                    pageinfo = gameService.getFilterCategorie(filter.getCategorieIds()[0],pageinfo.currentPage, 20);
                    games=pageinfo.gamePage;
                    break;
                case 2:
                    games = gameService.getFilter2Categories(filter.getCategorieIds()[0], filter.getCategorieIds()[1]);
                    break;
                default: games = gameService.getFilter2Categories(filter.getCategorieIds()[0], filter.getCategorieIds()[1]);
                    break;
            }
        }
        else{
            pageinfo = gameService.getGames(pageinfo.currentPage, 20);
            games =  pageinfo.gamePage;
        }

        mav.addObject("categories", categories);
        mav.addObject("games", games);
        mav.addObject("filter", filter);
        mav.addObject("pageinfo", pageinfo);
        return mav;
    }
//    @RequestMapping(value = "/categorie",method = RequestMethod.POST)
//    public ModelAndView PostCategorie(Filter filter, @ModelAttribute("pageinfo") PageInfo pageinfo){
//        ModelAndView mav = new ModelAndView("categorie");
//        List<Categorie> categories = categorieService.getCategories();
//        pageinfo = gameService.getGames(pageinfo.currentPage, 20);
//        List <Game> games = null;
//        if (filter.getCategorieIds() !=null) {
//            switch (filter.getCategorieIds().length) {
//                case 0:
//                    games = pageinfo.gamePage;
//                    break;
//                case 1:
//                    games = gameService.getFilterCategorie(filter.getCategorieIds()[0]);
//                    break;
//                case 2:
//                    games = gameService.getFilter2Categories(filter.getCategorieIds()[0], filter.getCategorieIds()[1]);
//                    break;
//                default: games = gameService.getFilter2Categories(filter.getCategorieIds()[0], filter.getCategorieIds()[1]);
//                    break;
//            }
//        }
//        else{
//            games =  pageinfo.gamePage;
//        }
//        mav.addObject("categories", categories);
//        mav.addObject("games", games);
//        mav.addObject("filter", filter);
//        mav.addObject("pageinfo", pageinfo);
//        return mav;
//    }
@RequestMapping(value = "/categorie/{page}",method = RequestMethod.GET)
public ModelAndView PostCategorie(@ModelAttribute("filter")Filter filter,@ModelAttribute("pageinfo") PageInfo pageinfo, @PathVariable("page") int page, RedirectAttributes redirectAttributes){
    ModelAndView mav = new ModelAndView("redirect:/categorie");
    pageinfo.setCurrentPage(page);
    redirectAttributes.addFlashAttribute("filter", filter);
    redirectAttributes.addFlashAttribute("pageinfo",pageinfo);
    return mav;
}
}
