package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.*;
import be.thomasmore.graduaten.diceroll.helper.Converter;
import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import be.thomasmore.graduaten.diceroll.objects.*;
import be.thomasmore.graduaten.diceroll.service.RentOrderService;
import be.thomasmore.graduaten.diceroll.service.SaleOrderService;
import be.thomasmore.graduaten.diceroll.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    // Attributes
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService _userService;
    private final SaleOrderService _saleOrderService;
    private final RentOrderService _rentOrderService;
    private final ModelMapper _mapper;
    private final BCryptPasswordEncoder _encoder;


    // Constructor
    @Autowired
    public UserController(UserService userService, SaleOrderService saleOrderService, RentOrderService rentOrderService, ModelMapper mapper, BCryptPasswordEncoder encoder) {
        this._userService = userService;
        this._saleOrderService = saleOrderService;
        this._rentOrderService = rentOrderService;
        this._mapper = mapper;
        this._encoder = encoder;
    }

    // Methods
    @GetMapping("/user/info")
    public ModelAndView myInfo(UserDeleteAccountDTO userDeleteAccountDTO) {
        ModelAndView mv = new ModelAndView("user/info");

        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);
        mv.addObject("userDeleteAccountDTO", userDeleteAccountDTO);

        return mv;
    }

    @GetMapping("/user/edit-accountinfo")
    public ModelAndView editAccountInfo(UserChangeEmailDTO userChangeEmailDTO, UserChangePasswordDTO userChangePasswordDTO) {
        ModelAndView mv = new ModelAndView("user/edit-accountinfo");

        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("userChangeEmailDTO", userChangeEmailDTO);
        mv.addObject("userChangePasswordDTO", userChangePasswordDTO);
        mv.addObject("authUser", authUser);

        return mv;
    }

    @PostMapping("/user/edit-email")
    public ModelAndView editEmail(@ModelAttribute("userChangeEmailDTO") @Valid UserChangeEmailDTO userChangeEmailDTO, BindingResult bindingResult) {

        // Validate user password
        if (userChangeEmailDTO.getVerifyPassword() != "" && !_encoder.matches(userChangeEmailDTO.getVerifyPassword(), UserInformation.getAuthenticatedUser().getPassword())) {
            bindingResult.addError(new FieldError("userChangeEmailDTO", "verifyPassword", "Het opgegeven wachtwoord is niet correct"));
        }

        // Validate if email address is already in use
        else if (_userService.userExists(userChangeEmailDTO.getNewEmail())) {
            bindingResult.addError(new FieldError("userChangeEmailDTO", "newEmail", "Het opgegeven emailadres is reeds in gebruik"));
        }

        // Validate form input for errors
        if (bindingResult.hasErrors()) {
            //Return original page with updated model to show errors
            return editAccountInfo(userChangeEmailDTO, new UserChangePasswordDTO());
        }

        // Update record with new information
        try {
            log.info(">> Updated object: " + _userService.updateAuthUser(userChangeEmailDTO));
        }
        catch (Exception exception) {
            log.info(">> Exception encountered while updating the User model");
        }

        return new ModelAndView("redirect:/user/info");
    }

    @PostMapping("/user/edit-password")
    public ModelAndView editPassword(@ModelAttribute("userChangePasswordDTO") @Valid UserChangePasswordDTO userChangePasswordDTO, BindingResult bindingResult) {

        // Validate user password
        if (userChangePasswordDTO.getOldPassword() != "" && !_encoder.matches(userChangePasswordDTO.getOldPassword(), UserInformation.getAuthenticatedUser().getPassword())) {
            bindingResult.addError(new FieldError("userChangePasswordDTO", "oldPassword", "Het opgegeven wachtwoord is niet correct"));
        }

        //Check matching passwords
        else if (userChangePasswordDTO.getNewPassword() != "" && userChangePasswordDTO.getConfirmPassword() != "") {

            if (!userChangePasswordDTO.getNewPassword().equals(userChangePasswordDTO.getConfirmPassword())) {

                //Inject error message in validation result for confirmPassword field
                bindingResult.addError(new FieldError("userChangePasswordDTO","confirmPassword","Passwords do not match"));
            }
        }

        // Validate form input for errors
        if (bindingResult.hasErrors()) {
            //Return original page with updated model to show errors
            return editAccountInfo(new UserChangeEmailDTO(), userChangePasswordDTO);
        }

        // Update record with new information
        try {
            log.info(">> Updated object: " + _userService.updateAuthUser(userChangePasswordDTO));
        }
        catch (Exception exception) {
            log.info(">> Exception encountered while updating the User model");
        }

        return new ModelAndView("redirect:/user/info");
    }

    @GetMapping("/user/edit-personalinfo")
    public ModelAndView editPersonalInfo(UserChangePersonalDTO userChangePersonalDTO) {
        ModelAndView mv = new ModelAndView("user/edit-personalinfo");

        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        // Inject current information into DTO
        _mapper.map(authUser,userChangePersonalDTO);

        // Add DTO to page
        mv.addObject("userChangePersonalDTO", userChangePersonalDTO);

        return mv;
    }

    @PostMapping("/user/edit-personalinfo")
    public ModelAndView editPersonalInfo(@ModelAttribute("userChangePersonalDTO") @Valid UserChangePersonalDTO userChangePersonalDTO, BindingResult bindingResult) {

        // Validate form input for errors
        if (bindingResult.hasErrors()) {
            //Return original page with updated model to show errors
            ModelAndView mv = new ModelAndView("user/edit-personalinfo");
            mv.addObject("userChangeContactDTO", userChangePersonalDTO);
            return mv;
        }

        // Update record with new information
        try {
            log.info(">> Updated object: " + _userService.updateAuthUser(userChangePersonalDTO));
        }
        catch (Exception exception) {
            log.info(">> Exception encountered while updating the User model");
        }

        return new ModelAndView("redirect:/user/info");
    }

    @GetMapping("/user/edit-contactinfo")
    public ModelAndView editContactInfo(UserChangeContactDTO userChangeContactDTO) {
        ModelAndView mv = new ModelAndView("user/edit-contactinfo");

        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        // Inject current information into DTO
        _mapper.map(authUser, userChangeContactDTO);

        // Add DTO to page
        mv.addObject("userChangeContactDTO", userChangeContactDTO);

        return mv;
    }

    @PostMapping("/user/edit-contactinfo")
    public ModelAndView editContactInfo(@ModelAttribute("userChangeContactDTO") @Valid UserChangeContactDTO userChangeContactDTO, BindingResult bindingResult) {

        // Validate form input for errors
        if (bindingResult.hasErrors()) {
            //Return original page with updated model to show errors
            ModelAndView mv = new ModelAndView("user/edit-contactinfo");
            mv.addObject("userChangeContactDTO", userChangeContactDTO);
            return mv;
        }

        // Update record with new information
        try {
            log.info(">> Updated object: " + _userService.updateAuthUser(userChangeContactDTO));
        }
        catch (Exception exception) {
            log.info(">> Exception encountered while updating the User model");
        }

        return new ModelAndView("redirect:/user/info");
    }

    @PostMapping("/user/delete-account")
    public ModelAndView deleteAccount(UserDeleteAccountDTO userDeleteAccountDTO) {

        // Verify user password
        if (userDeleteAccountDTO.getPassword() != "") {
            if (_encoder.matches(userDeleteAccountDTO.getPassword(), UserInformation.getAuthenticatedUser().getPassword())) {
                log.info(">> Password correct, proceed with account deletion");

                _userService.delete(UserInformation.getAuthenticatedUser());

                return new ModelAndView("redirect:/logout");
            }
        }

        return new ModelAndView("/user/info");
    }

    @GetMapping("/user/orders")
    public ModelAndView userOrders() {
        // Create ModelAndView return-model
        ModelAndView mv = new ModelAndView("user/orders");

        // Get current authenticated user and add it to the the model for the NavBar
        User authUser = UserInformation.getAuthenticatedUser();

        mv.addObject("authUser", authUser);

        // Get a list of all sale orders for the current user and add to the model
        List<SaleOrderDisplayModel> saleOrders = convertSaleOrdersToDisplayModel(_saleOrderService.findAllByUser(authUser));

        mv.addObject("saleOrders", saleOrders);

        // Get a list of all rent orders for the current user and add to the model
        List<RentOrderDisplayModel> rentOrders = convertRentOrdersToDisplayModel(_rentOrderService.findAllByUser(authUser));

        mv.addObject("rentOrders", rentOrders);

        // Return ModelAndView return-model
        return mv;
    }

    @GetMapping("user/sale/{id}")
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

    @GetMapping("user/rent/{id}")
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

    // Source: https://stackoverflow.com/questions/55654740/download-file-xml-with-spring-boot
    @GetMapping("/user/download")
    public ResponseEntity downloadUserInformation() throws IOException {
        File file = Converter.convertUserToXML(UserInformation.getAuthenticatedUser());

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData(file.getName(), file.getName());
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        byte[] content = Files.readAllBytes(file.toPath());
        return new ResponseEntity(content, headers, HttpStatus.OK);
    }

    // Helper Methods

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
}
