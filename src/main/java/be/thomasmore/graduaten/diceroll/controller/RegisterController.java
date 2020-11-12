package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.objects.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    //PreProcess input data before handling it
    //Trims spaces from input to prevent faulty strings
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("register")
    public ModelAndView register(UserDTO userDTO)
    {
        ModelAndView mv = new ModelAndView("register");
        mv.addObject("userDTO",userDTO);
        return mv;
    }

    @PostMapping("register")
    public ModelAndView registerUser(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()) {
            log.info(">> Controller has detected errors");

            ModelAndView mv = new ModelAndView("register");
            mv.addObject("userDTO", userDTO);
            return mv;
        }

        log.info(">> Create new object: {}", userDTO.toString());

        return new ModelAndView("register");
    }
}
