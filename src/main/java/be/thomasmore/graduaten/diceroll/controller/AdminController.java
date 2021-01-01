package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.*;
import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import be.thomasmore.graduaten.diceroll.objects.*;
import be.thomasmore.graduaten.diceroll.service.RentOrderService;
import be.thomasmore.graduaten.diceroll.service.SaleOrderService;
import be.thomasmore.graduaten.diceroll.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.Set;

@Controller
public class AdminController {

    // Attributes
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    private final UserService _userService;
    private final SaleOrderService _saleOrderService;
    private final RentOrderService _rentOrderService;
    private final ModelMapper _mapper;

    // Constructor
    @Autowired
    public AdminController(UserService userService, SaleOrderService saleOrderService, RentOrderService rentOrderService, ModelMapper mapper) {
        this._userService = userService;
        this._saleOrderService = saleOrderService;
        this._rentOrderService = rentOrderService;
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
    @ResponseStatus(value = HttpStatus.OK)
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

        // Create view model and redirect to HTTP GET methods
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

    @GetMapping("/admin/sale")
    public ModelAndView orders(@ModelAttribute("saleOrderFilter") SaleOrderFilter saleFilter) {
        // Create ModelAndView return-model
        ModelAndView mv = new ModelAndView("admin/sale");

        // Get current authenticated user and add it to the the model for the NavBar
        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        // Extract page information from filter objects
        Pageable salePageable = PageRequest.of(saleFilter.getCurrentPage(), saleFilter.getObjectsPerPage());

        // Retrieve paged database result
        Page<SaleOrder> saleResult = _saleOrderService.findAll(saleFilter, salePageable);

        // Convert resulting list to Display Models
        List<SaleOrderDisplayModel> displaySaleOrders = convertSaleOrdersToDisplayModel(saleResult.toList());

        // Add list to View Model
        mv.addObject("displaySaleOrders", displaySaleOrders);

        // Update filter object with result information
        saleFilter.setTotalPages(saleResult.getTotalPages());

        // Get a list of all users and add it to the View Model (for filter)
        mv.addObject("applicationUsers", getAllUsersAsDisplayModel());

        // Add filter object to View Model
        mv.addObject("saleOrderFilter", saleFilter);

        // Return ModelAndView return-model
        return mv;
    }

    @GetMapping("/admin/rent")
    public ModelAndView orders(@ModelAttribute("rentOrderFilter") RentOrderFilter rentFilter) {
        // Create ModelAndView return-model
        ModelAndView mv = new ModelAndView("admin/rent");

        // Get current authenticated user and add it to the the model for the NavBar
        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        // Extract page information from filter objects
        Pageable rentPageable = PageRequest.of(rentFilter.getCurrentPage(), rentFilter.getObjectsPerPage());

        // Retrieve paged database result
        Page<RentOrder> rentResult = _rentOrderService.findAll(rentFilter, rentPageable);

        // Convert resulting list to Display Models
        List<RentOrderDisplayModel> displayRentOrders = convertRentOrdersToDisplayModel(rentResult.toList());

        // Add list to View Model
        mv.addObject("displayRentOrders", displayRentOrders);

        // Update filter object with result information
        rentFilter.setTotalPages(rentResult.getTotalPages());

        // Get a list of all users and add it to the View Model (for filter)
        mv.addObject("applicationUsers", getAllUsersAsDisplayModel());

        // Add filter object to View Model
        mv.addObject("rentOrderFilter", rentFilter);

        // Return ModelAndView return-model
        return mv;
    }

    @GetMapping("admin/sale/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView getSaleOrderDetails(@PathVariable("id") int id, ModelAndView mv) {

        mv.setViewName("saleOrderDetailsPartial");

        // Create empty instance of display model
        SaleOrderDetailsDisplayModel orderDetailsDisplayModel = new SaleOrderDetailsDisplayModel();

        // Get order details
        SaleOrder orderDetail = _saleOrderService.getSaleOrder(id);

        // Map matching attributes
        _mapper.map(orderDetail, orderDetailsDisplayModel);

        // Convert games to DTO models
        orderDetailsDisplayModel.setSoldGames(convertSoldGameToDTO(orderDetail.getSoldGames()));

        // Add display model to page model
        mv.addObject("displayModel", orderDetailsDisplayModel);

        return mv;
    }

    @GetMapping("admin/rent/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView getRentOrderDetails(@PathVariable("id") int id, ModelAndView mv) {

        mv.setViewName("rentOrderDetailsPartial");

        // Create empty instance of display model
        RentOrderDetailsDisplayModel orderDetailsDisplayModel = new RentOrderDetailsDisplayModel();

        // Get order details
        RentOrder orderDetail = _rentOrderService.findById(id).get();

        // Map matching attributes
        _mapper.map(orderDetail, orderDetailsDisplayModel);

        // Convert games to DTO models
        orderDetailsDisplayModel.setRentedGames(convertRentedGameToDTO(orderDetail.getRentedGames()));

        // Add display model to page model
        mv.addObject("displayModel", orderDetailsDisplayModel);

        return mv;
    }

    @PostMapping("admin/sale/filter")
    public ModelAndView SaleOrdersFiltered(@ModelAttribute("saleOrderFilter") SaleOrderFilter saleOrderFilter, RedirectAttributes redirectAttributes) {

        // Create View Model and redirect to HTTP GET method
        ModelAndView mv = new ModelAndView("redirect:/admin/sale");

        // Add filter object to redirect attributes as flash attribute
        redirectAttributes.addFlashAttribute("saleOrderFilter", saleOrderFilter);

        // Redirect to page with filter object
        return mv;
    }

    @PostMapping("admin/rent/filter")
    public ModelAndView RentOrdersFiltered(@ModelAttribute("rentOrderfilter") RentOrderFilter rentOrderFilter, RedirectAttributes redirectAttributes) {

        // Create View Model and redirect to HTTP GET method
        ModelAndView mv = new ModelAndView("redirect:/admin/rent");

        // Add filter object to redirect attributes as flash attribute
        redirectAttributes.addFlashAttribute("rentOrderFilter", rentOrderFilter);

        // Redirect to page with filter object
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

    private List<UserDisplayModel> getAllUsersAsDisplayModel() {

        // Get all users from database
        List<User> allUsers = _userService.findAll();

        // Create empty list as return model
        List<UserDisplayModel> displayUsers = new ArrayList<UserDisplayModel>();

        // Map all user entities to DTO models
        for (User user : allUsers) {
            // If user entity was anonymized, skip it
            if (!user.getEmail().equals("anonymized")) {

                // Create empty DTO model
                UserDisplayModel mappedUser = new UserDisplayModel();

                // Map matching attributes
                _mapper.map(user, mappedUser);

                // Add new DTO object to return model
                displayUsers.add(mappedUser);
            }
        }

        return displayUsers;
    }

    private List<SoldGameDTO> convertSoldGameToDTO(Set<SoldGame> soldGames) {

        // Create empty instance of an ArrayList as return model
        List<SoldGameDTO> returnModel = new ArrayList<SoldGameDTO>();

        // Convert each object in the list to a DTO
        for (SoldGame soldGame : soldGames ) {
            // Create instance of DTO
            SoldGameDTO dto = new SoldGameDTO();

            // Map attributes
            _mapper.map(soldGame, dto);

            // Add DTO to return model
            returnModel.add(dto);
        }

        return returnModel;
    }

    private List<RentedGameDTO> convertRentedGameToDTO(Set<RentedGame> rentedGames) {

        // Create empty instance of an ArrayList as return model
        List<RentedGameDTO> returnModel = new ArrayList<RentedGameDTO>();

        // Convert each object in the list to a DTO
        for (RentedGame rentedGame : rentedGames ) {
            // Create instance of DTO
            RentedGameDTO dto = new RentedGameDTO();

            // Map attributes
            _mapper.map(rentedGame, dto);

            // Add DTO to return model
            returnModel.add(dto);
        }

        return returnModel;
    }

    private List<SaleOrderDisplayModel> convertSaleOrdersToDisplayModel(List<SaleOrder> saleOrders) {

        // Create empty list as return model
        List<SaleOrderDisplayModel> displaySaleOrders = new ArrayList<SaleOrderDisplayModel>();

        // Map all entities to display models
        for (SaleOrder saleOrder : saleOrders) {
            // Create empty display model
            SaleOrderDisplayModel saleOrderDisplay = new SaleOrderDisplayModel();

            // Map matching attributes
            _mapper.map(saleOrder, saleOrderDisplay);

            // Add new display model to the return model
            displaySaleOrders.add(saleOrderDisplay);
        }

        return displaySaleOrders;
    }

    private List<RentOrderDisplayModel> convertRentOrdersToDisplayModel(List<RentOrder> rentOrders) {

        // Create empty list as return model
        List<RentOrderDisplayModel> displayRentOrders = new ArrayList<RentOrderDisplayModel>();

        // Map all entities to display models
        for (RentOrder rentOrder : rentOrders) {
            // Create empty display model
            RentOrderDisplayModel rentOrderDisplay = new RentOrderDisplayModel();

            // Map matching attributes
            _mapper.map(rentOrder, rentOrderDisplay);

            // Add new display model to the return list
            displayRentOrders.add(rentOrderDisplay);
        }

        return displayRentOrders;
    }
}
