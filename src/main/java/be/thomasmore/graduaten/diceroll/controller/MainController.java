package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.Categorie;
import be.thomasmore.graduaten.diceroll.entity.Game;
import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.objects.RentGameDTO;
import be.thomasmore.graduaten.diceroll.objects.TestDTO;
import be.thomasmore.graduaten.diceroll.service.CategorieService;
import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import be.thomasmore.graduaten.diceroll.service.GameService;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    List<TestDTO> testen = new ArrayList<TestDTO>();
    List<RentGameDTO> rentGameDTOS = new ArrayList<RentGameDTO>();
    int i = 0;
    @Autowired
    GameService gameService;

    @RequestMapping("/")
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView("index");

        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        session.setAttribute("test",testen);
        session.setAttribute("RentGameDTOS",rentGameDTOS );
        i++;
        List<Game> games = gameService.getHighestRated();
        mv.addObject("games", games);
        mv.addObject("i",i);
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

    @RequestMapping("/RentGame")
    public ModelAndView rentGame(HttpSession session, @RequestParam String id){
        Game game = gameService.getGameById(Long.parseLong(id));
        if (game.getStock_Rent() == 0)
        {
            ModelAndView mv  = new ModelAndView("stocksale");
            User authUser = UserInformation.getAuthenticatedUser();
            mv.addObject("authUser", authUser);
            mv.addObject("Game", game);
            return mv;
        }
        List<RentGameDTO> rentGameDTOS = (List<RentGameDTO>)session.getAttribute("RentGameDTOS");
        RentGameDTO rentGameDTO = new RentGameDTO();
        rentGameDTO.setId(id);
        rentGameDTO.setPrice(game.getPrice_Rent());
        rentGameDTO.setTitle(game.getTitle());
        rentGameDTOS.add(rentGameDTO);
        session.setAttribute("RentGameDTOS",rentGameDTOS);
        ModelAndView mv = new ModelAndView("winkelmand");
        return mv;
    }
    @RequestMapping("/categories")
        public ModelAndView categorieToCart(HttpSession session, @RequestParam String id,@RequestParam int aantal){
        Game game = gameService.getGameById(Long.parseLong(id));
        if (game.getStock_Sale()-aantal <= 0){
            ModelAndView mv  = new ModelAndView("stocksale");
            User authUser = UserInformation.getAuthenticatedUser();
            mv.addObject("authUser", authUser);
            mv.addObject("Game", game);
            return mv;
        }
        List<TestDTO> testen = (List<TestDTO>) session.getAttribute("test");
        if(testen == null){testen = new ArrayList<TestDTO>();}
        TestDTO test = new TestDTO();
        test.setId(id);
        test.setAantal(aantal);
        test.setTitle(game.getTitle());
        test.setPrice(game.getPrice_Sale());
        int i=0;
        boolean exists = false;
        for (TestDTO test1:testen)
        {
            if (test1.getId().equals(test.getId()) ){
                exists = true;
                int res = test1.getAantal()+test.getAantal();
                test1.setAantal(res);
            }
        }
        if (!exists){
        testen.add(test);
        }
        session.setAttribute("test",testen);
        ModelAndView mv = new ModelAndView("winkelmand");
        return mv;
    }


    @GetMapping("/winkelmands")
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
