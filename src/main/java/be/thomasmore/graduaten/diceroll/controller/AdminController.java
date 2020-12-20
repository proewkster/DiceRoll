package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import be.thomasmore.graduaten.diceroll.objects.UserMgmtDTO;
import be.thomasmore.graduaten.diceroll.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    // Attributes
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private final UserService _userService;
    private final ModelMapper _mapper;

    // Constructor
    @Autowired
    public AdminController(UserService userService, ModelMapper mapper) {
        this._userService = userService;
        this._mapper = mapper;
    }

    // ActionMethods
    @GetMapping("/admin/users")
    public ModelAndView users(@ModelAttribute("selectedUser") @Valid UserMgmtDTO selectedUser,
                              @ModelAttribute("validationError") String validationError,
                              @ModelAttribute("validationMessage") String validationMessage
    ) {

        // Create ModelAndView return-model
        ModelAndView mv = new ModelAndView("admin/users");

        // Get current authenticated user and add it to the the model for the NavBar
        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        // Get a list of all user accounts in the database and add it to the model for display
        List<UserMgmtDTO> displayUsers = getAllUsersAsDTO();

        mv.addObject("displayUsers", displayUsers);

        // Add an empty instance of selected user object
        if (selectedUser == null) {
            selectedUser = new UserMgmtDTO();
        }

        mv.addObject("selectedUser", selectedUser);

        // Add validation messages to view model
        mv.addObject("validationError", validationError);
        mv.addObject("validationMessage", validationMessage);

        // Return ModelAndView return-model
        return mv;
    }

    @GetMapping("/admin/users/edit/{id}")
    @ResponseStatus(value= HttpStatus.OK)
    public ModelAndView getSelectedUserPartial(@PathVariable("id") int id, ModelAndView mv) {

        mv.setViewName("admin/editUserPartial");

        // Get selected user
        UserMgmtDTO selectedUser = new UserMgmtDTO();

        User tempUserEntity = _userService.findUserById(id).get();

        _mapper.map(tempUserEntity, selectedUser);

        selectedUser.setAuthorities(tempUserEntity.getAuthorities());

        selectedUser.setUserRoles();

        // Add object to model and return
        mv.addObject("selectedUser", selectedUser);

        return mv;
    }

    @PostMapping("/admin/users/edit")
    public ModelAndView updateUser(@ModelAttribute("selectedUser") @Valid UserMgmtDTO selectedUser, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // Create view model and add display model as flash attribute
        ModelAndView mv = new ModelAndView("redirect:/admin/users");

        // Check if user is authenticated
        if (UserInformation.getAuthenticatedUser() == null) {
            log.info(">> Anonymous user attempted admin level interactions with database");

            String errorMessage = "U bent niet ingelogd, de actie is niet toegestaan";

            // Inject error message in validation result
            bindingResult.addError(new FieldError("selectedUser", "generalError", errorMessage));

            // Inject error message in view model
            redirectAttributes.addFlashAttribute("validationError", errorMessage);
        }

        // Check if user is admin
        else if (!UserInformation.checkAdminUser()) {
            log.info(">> User with ID " + UserInformation.getAuthenticatedUser().getUserId() + " attempted to change user account with ID " + selectedUser.getUserId() +
                    ", but is not an admin");

            String errorMessage = "U heeft niet voldoende machtiging om een gebruikersaccount aan te passen";

            // Inject error message in validation result
            bindingResult.addError(new FieldError("selectedUser", "generalError", errorMessage));

            // Inject error message in view model
            redirectAttributes.addFlashAttribute("validationError", errorMessage);
        }

        // Validate form input for errors
        if (bindingResult.hasErrors()) {
            log.info(">> Controller has detected validation errors");

            // Add generic error to display model if none is present
            // This means the display model has validation errors (in-form validation to be implemented)
            if (selectedUser.getErrors().isEmpty()) {
                redirectAttributes.addFlashAttribute("validationError", "Er is iets misgegaan tijdens het aanpassen van het gebruikersaccount");
            }

            // Return original page with redirected display model
            return mv;
        }

        // No errors detected during validation, proceed with changing the user account
        try {
            User temp = _userService.updateUser(selectedUser);

            log.info(">> Updated user account with id: {}", selectedUser.getUserId());

            redirectAttributes.addFlashAttribute("validationMessage", "Gebruikersaccount '" + selectedUser.getEmail() + "' successvol aangepast");
        }
        catch(Exception ex) {
            log.info(">> Exception encountered while updating user account with id: {}", selectedUser.getUserId());
            log.info(ex.getMessage());

            redirectAttributes.addFlashAttribute("validationError", "Er is iets misgegaan tijdens het aanpassen van het gebruikersaccount");
        }

        return mv;
    }

    @GetMapping("/admin/users/delete/{id}")
    public ModelAndView deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        // Initialize error-tracker
        boolean error = false;

        // Create view model
        ModelAndView mv = new ModelAndView("redirect:/admin/users");

        // Check if user is authenticated
        if (UserInformation.getAuthenticatedUser() == null) {
            error = true;

            log.info(">> Anonymous user attempted admin level interactions with database");

            String errorMessage = "U bent niet ingelogd, de actie is niet toegestaan";

            // Inject error message in view model
            redirectAttributes.addFlashAttribute("validationError", errorMessage);
        }

        // Check if user is admin
        else if (!UserInformation.checkAdminUser()) {
            error = true;

            log.info(">> User with ID " + UserInformation.getAuthenticatedUser().getUserId() + " attempted to remove user account with ID " + id +
                    ", but is not an admin");

            String errorMessage = "U heeft niet voldoende machtiging om een gebruikersaccount aan te passen";

            // Inject error message in view model
            redirectAttributes.addFlashAttribute("validationError", errorMessage);
        }

        if (!error) {
            // Remove user account
            try {
                _userService.deleteUserById(id);
            }
            catch(Exception ex) {
                log.info(">> Exception encountered while removing user account with id: {}", id);
                log.info(ex.getMessage());

                redirectAttributes.addFlashAttribute("validationError", "Er is iets misgegaan tijdens het verwijderen van het gebruikersaccount");
            }

            // Set validation message
            redirectAttributes.addFlashAttribute("validationMessage", "Gebruiker met id: " + id + " succesvol verwijderd");
        }

        return mv;
    }

    @GetMapping("/admin/orders")
    public ModelAndView orders() {
        // Create ModelAndView return-model
        ModelAndView mv = new ModelAndView("admin/orders");

        // Get current authenticated user and add it to the the model for the NavBar
        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        // Return ModelAndView return-model
        return mv;
    }

    // Helper Methods

    private List<UserMgmtDTO> getAllUsersAsDTO() {

        // Get all users from database
        List<User> allUsers = _userService.findAll();

        // Create empty list as return model
        List<UserMgmtDTO> displayUsers = new ArrayList<UserMgmtDTO>();

        // Map all user entities to DTO models
        for (User user : allUsers) {
            // If user entity was anonymized, skip it
            if (!user.getEmail().equals("anonymized")) {

                // Create empty DTO model
                UserMgmtDTO mappedUser = new UserMgmtDTO();

                // Map matching attributes
                _mapper.map(user, mappedUser);

                // Map authorities list
                mappedUser.setAuthorities(user.getAuthorities());

                // Set boolean properties for user roles
                mappedUser.setUserRoles();

                // Add new DTO object to return model
                displayUsers.add(mappedUser);
            }
        }

        return displayUsers;
    }
}
