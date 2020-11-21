package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
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
        if (getPrincipal() != null) {
            return new ModelAndView("authenticated");
        }

        //Reset all parameters
        String errorMessage = null;
        String logoutMessage = null;

        //Set parameters if login error occurred
        if (error) {
            errorMessage = "Wrong username and/or password";
        }

        //Set parameters if logout occurred
        if (logout) {
            logoutMessage = "You have been successfully logged out!";
        }

        //Add parameters to page model
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("errorMessage", errorMessage);
        mv.addObject("logoutMessage", logoutMessage);

        //Return login page
        return mv;
    }

    //Authentication page for login success
    @GetMapping("/authenticated")
    public String authenticated(Model model) {
        model.addAttribute("user",getPrincipal());
        return "authenticated";
    }

    //Get logged in user
    private User getPrincipal() {
        User authUser = null;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User) {
            authUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return authUser;
    }
}
