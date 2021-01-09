package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) boolean error,
            @RequestParam(value = "logout", required = false) boolean logout,
            Model model) {

        //If a user is already logged in, redirect to authenticated page
        if (UserInformation.getAuthenticatedUser() != null) {
            return new ModelAndView("");
        }

        //Reset all parameters
        String errorMessage = null;
        String logoutMessage = null;

        //Set parameters if login error occurred
        if (error) {
            errorMessage = "Onjuiste gebruikersnaam of wachtwoord!";
        }

        //Set parameters if logout occurred
        if (logout) {
            logoutMessage = "Je bent succesvol afgemeld.";
        }

        //Add parameters to page model
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("errorMessage", errorMessage);
        mv.addObject("logoutMessage", logoutMessage);

        //Return login page
        return mv;
    }
}
