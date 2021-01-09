package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import be.thomasmore.graduaten.diceroll.objects.RegisterUserDTO;
import be.thomasmore.graduaten.diceroll.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    private final UserService _userService;

    @Autowired
    public RegisterController(UserService userService) {
        this._userService = userService;
    }

    //PreProcess input data before handling it
    //Trims spaces from input to prevent faulty strings
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping("register")
    public ModelAndView register(RegisterUserDTO registerUserDTO)
    {
        ModelAndView mv = new ModelAndView("register");

        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);
        mv.addObject("userDTO", registerUserDTO);
        return mv;
    }

    @PostMapping("register")
    public ModelAndView registerUser(@ModelAttribute("userDTO") @Valid RegisterUserDTO registerUserDTO, BindingResult bindingResult)
    {

        //Check if user already exists
        if (_userService.userExists(registerUserDTO.getEmail())) {

            //Inject error message in validation result for email field
            bindingResult.addError(new FieldError("userDTO","email","Dit emailadres is reeds in gebruik"));
        }

        //Check matching passwords
        if (registerUserDTO.getPassword() != null && registerUserDTO.getConfirmPassword() != null) {

            if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {

                //Inject error message in validation result for confirmPassword field
                bindingResult.addError(new FieldError("userDTO","confirmPassword","Wachtwoorden komen niet overeen"));
            }
        }

        //Validate form input for errors
        if(bindingResult.hasErrors()) {
            log.info(">> Controller has detected errors");

            //Return original page with updated model to show errors
            ModelAndView mv = new ModelAndView("register");
            mv.addObject("userDTO", registerUserDTO);
            return mv;
        }

        try {
            log.info(">> Create new object: {}", _userService.register(registerUserDTO).toString());

            return new ModelAndView("redirect:/");
        }
        catch (Exception exception) {
            log.info(">> Exception encountered while creating the User data model.");
        }

        return new ModelAndView("register");
    }
}
