package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.Game;
import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import be.thomasmore.graduaten.diceroll.objects.Search;
import be.thomasmore.graduaten.diceroll.service.GameService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GameController {
@Autowired
    GameService gameService;
@Autowired
    public GameController() {
}

    @RequestMapping(value = "/admin/game", method = RequestMethod.GET)
    public ModelAndView GetGame(Search search){
        ModelAndView mv = new ModelAndView("admin/game");
        List<Game> games = null;
        if (search.getId() == null){
            games = gameService.getGamesByTitle(search.getKeyword());
        }else {
            games = gameService.getGamesById(search.getId());
        }
        User authUser = UserInformation.getAuthenticatedUser();
        mv.addObject("authUser", authUser);
        mv.addObject("games",games);
        mv.addObject("search",search);
        return mv;
    }
   // @RequestMapping(value = "/admin/game",method = RequestMethod.POST)
    //public String PostGame(Model model,@RequestParam String keyword){
   // return "admin/game";
    //}

    //@RequestMapping("/addGame")
    //public String AddGame(Model model){
        //return "addGame";
    //}

    private static final Logger log = LoggerFactory.getLogger(GameController.class);


    //PreProcess input data before handling it
    //Trims spaces from input to prevent faulty strings
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("/admin/addGame")
    public ModelAndView register(Game game)
    {
        ModelAndView mv = new ModelAndView("admin/addGame");
        User authUser = UserInformation.getAuthenticatedUser();
        mv.addObject("authUser", authUser);
        mv.addObject("game",game);
        return mv;
    }

    @PostMapping("/admin/addGame")
    public ModelAndView registerUser(@ModelAttribute("game") @Valid Game game, BindingResult bindingResult) {

        //Validate form input for errors
        if (bindingResult.hasErrors()) {
            log.info(">> Controller has detected errors");

            ModelAndView mv = new ModelAndView("admin/addGame");
            mv.addObject("game", game);
            return mv;
        }
            gameService.saveGame(game);

        return new ModelAndView("admin/addGame");
    }
    @RequestMapping("/admin/deleteGame/{id}")
    public ModelAndView deleteGame(@PathVariable Long id){
        Game game = gameService.getGameById(id);
        game.setIgnore(true);
        gameService.saveGame(game);
        ModelAndView mv = new ModelAndView("redirect:/admin/game");
        return mv;
    }

    public ModelMapper mapper;
    @RequestMapping( value = "/admin/editGame",method = RequestMethod.GET)
    public ModelAndView editGame(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("admin/editGame");
        Game game = gameService.getGameById(id);
        User authUser = UserInformation.getAuthenticatedUser();
        mv.addObject("authUser", authUser);
        mv.addObject( "game",game);
        return mv;
    }
    @RequestMapping(value = "/admin/editGame", method = RequestMethod.POST)
        public ModelAndView saveGame(@ModelAttribute("game") @Valid Game game, BindingResult bindingResult ){
        User authUser = UserInformation.getAuthenticatedUser();

        if (bindingResult.hasErrors()) {
            log.info(">> Controller has detected errors");

            ModelAndView mv = new ModelAndView("admin/editGame");
            mv.addObject("game", game);
            mv.addObject("authUser", authUser);
            return mv;
        }
        try {
        gameService.saveGame(game);
        return new ModelAndView("redirect:/admin/game");
        }
        catch (Exception exception){
            ModelAndView mv = new ModelAndView("admin/editGame");
            mv.addObject("game", game);
            mv.addObject("authUser", authUser);
            return mv;
        }


    }

}
