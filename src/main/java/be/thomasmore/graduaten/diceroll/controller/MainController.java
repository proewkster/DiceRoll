package be.thomasmore.graduaten.diceroll.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/contact")
    public String contact(){return "contact";}

    @RequestMapping("/overons")
    public String overons(){return "overons";}

    @RequestMapping("/categorie")
    public String categorie(){return "categorie";}
}
