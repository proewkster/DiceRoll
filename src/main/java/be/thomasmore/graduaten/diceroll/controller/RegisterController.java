package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @RequestMapping("register")
    public String register()
    {
        return "register";
    }

    @PostMapping("showuser")
    public ModelAndView showUser(User user)
    {
        ModelAndView mv = new ModelAndView("showuser");
        mv.addObject("user",user);
        return mv;
    }
}
