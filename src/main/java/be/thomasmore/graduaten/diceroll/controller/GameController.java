package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.Game;
import be.thomasmore.graduaten.diceroll.objects.GameDTO;
import be.thomasmore.graduaten.diceroll.objects.Search;
import be.thomasmore.graduaten.diceroll.service.GameService;
import be.thomasmore.graduaten.diceroll.service.UserServiceImpl;
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

    @RequestMapping(value = "/game", method = RequestMethod.GET)
    public ModelAndView GetGame(Search search){
        ModelAndView mv = new ModelAndView("game");
        List<Game> games = null;
        if (search.getId() == null){
            games = gameService.getGamesByTitle(search.getKeyword());
        }else {
            games = gameService.getGamesById(search.getId());
        }
        mv.addObject("games",games);
        mv.addObject("search",search);
        return mv;
    }
    @RequestMapping(value = "/game",method = RequestMethod.POST)
    public String PostGame(Model model,@RequestParam String keyword){
    return "game";
    }

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

    @GetMapping("addGame")
    public ModelAndView register(Game game)
    {
        ModelAndView mv = new ModelAndView("addGame");
        mv.addObject("game",game);
        return mv;
    }

    @PostMapping("addGame")
    public ModelAndView registerUser(@ModelAttribute("game") @Valid Game game, BindingResult bindingResult) {


        //Validate form input for errors
        if (bindingResult.hasErrors()) {
            log.info(">> Controller has detected errors");

            ModelAndView mv = new ModelAndView("addGame");
            mv.addObject("game", game);
            return mv;
        }
            gameService.saveGame(game);

        return new ModelAndView("addGame");
    }
    @RequestMapping("deleteGame/{id}")
    public String deleteGame(@PathVariable Long id){
        gameService.deleteGame(id);
        return "redirect:/game";
    }

    public ModelMapper mapper;
    @RequestMapping( value = "/editGame",method = RequestMethod.GET)
    public ModelAndView editGame(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("editGame");
        Game game = gameService.getGameById(id);
        mv.addObject( "game",game);
        return mv;
    }
    @RequestMapping(value = "/editGame", method = RequestMethod.POST)
        public ModelAndView saveGame(@ModelAttribute("game") @Valid Game game, BindingResult bindingResult ){

        if (bindingResult.hasErrors()) {
            log.info(">> Controller has detected errors");

            ModelAndView mv = new ModelAndView("editGame");
            mv.addObject("game", game);
            return mv;
        }
        try {
        gameService.saveGame(game);
        return new ModelAndView("redirect:/game");
        }
        catch (Exception exception){
            ModelAndView mv = new ModelAndView("editGame");
            mv.addObject("game", game);
            return mv;
        }


    }

}
