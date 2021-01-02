package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.*;
import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import be.thomasmore.graduaten.diceroll.objects.OrderDTO;
import be.thomasmore.graduaten.diceroll.objects.RentGameDTO;
import be.thomasmore.graduaten.diceroll.objects.SessionGameDTO;
import be.thomasmore.graduaten.diceroll.objects.SoldGameDTO;
import be.thomasmore.graduaten.diceroll.service.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final ModelMapper mapper;
    @Autowired
    public OrderController(ModelMapper mapper){
        this.mapper = mapper;
    }
    @Autowired
    SoldGameService soldGameService;
    @Autowired
    GameService gameService;
    @Autowired
    SaleOrderService saleOrderService;
    @Autowired
    RentedGameService rentedGameService;
    @Autowired
    RentOrderService rentOrderService;
    @GetMapping("/order")
    public ModelAndView order(HttpSession session ){
        ModelAndView mv = new  ModelAndView("order");
        User authUser = UserInformation.getAuthenticatedUser();
        List<RentGameDTO> rentGameDTOS = (List<RentGameDTO>)session.getAttribute("RentGameDTOS");
        List<SessionGameDTO> testen = (List<SessionGameDTO>) session.getAttribute("test");
        for (RentGameDTO rentGameDTO:rentGameDTOS) {
            rentGameDTO.setGame(gameService.getGameById(Long.parseLong(rentGameDTO.getId())));
        }
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        mv.addObject("date",date);
        List<SoldGameDTO> soldGameDTOS = new ArrayList<SoldGameDTO>();
        if (testen == null){testen = new ArrayList<SessionGameDTO>();}
        for (SessionGameDTO sessionGameDTO :testen)
        {
            SoldGameDTO soldGameDTO = new SoldGameDTO();
            soldGameDTO.setAmount(sessionGameDTO.getAmount());
            soldGameDTO.setDiscount(sessionGameDTO.getDiscount());
            soldGameDTO.setPricePaid(sessionGameDTO.getPrice());
            soldGameDTO.setGame(gameService.getGameById(Long.parseLong(sessionGameDTO.getId())));
            soldGameDTOS.add(soldGameDTO);
        }
        OrderDTO orderDTO = new OrderDTO();

        mv.addObject("soldGameDTOS",soldGameDTOS);
        mv.addObject("orderDTO",orderDTO);
        mv.addObject("rentGameDTOS",rentGameDTOS);
        mv.addObject("authUser", authUser);
        return mv;
    }
    @PostMapping("/order")
    public ModelAndView orderresult(@ModelAttribute("orderDTO") @Valid OrderDTO orderDTO, BindingResult bindingResult,HttpSession session) throws ParseException {
        List<RentGameDTO> rentGameDTOS = (List<RentGameDTO>)session.getAttribute("RentGameDTOS");
        List<SessionGameDTO> sessionGameDTOS = (List<SessionGameDTO>) session.getAttribute("test");
        User authUser = UserInformation.getAuthenticatedUser();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
         Date date = simpleDateFormat.parse(orderDTO.getOrderDate());
            SaleOrder saleOrder = new SaleOrder();
            saleOrder.setOrderDate(date);
            saleOrder.setUser(authUser);
            saleOrder.setPaid(false);
            saleOrder.setDelivered(false);
            saleOrderService.save(saleOrder);


            if (bindingResult.hasErrors()) {
                log.info(">> Controller has detected errors");
                ModelAndView mv = new ModelAndView("order");
                return mv;
            }
            for (SessionGameDTO sessionGameDTO : sessionGameDTOS) {
                Game game = gameService.getGameById(Long.parseLong(sessionGameDTO.getId()));
                SoldGame soldGame = new SoldGame(saleOrder, game, sessionGameDTO.getPrice(), 0, sessionGameDTO.getAmount());
                soldGameService.saveSoldGame(soldGame);
            }

            RentOrder rentOrder = new RentOrder(authUser, false);
            rentOrderService.save(rentOrder);

            for (RentGameDTO rentGameDTO : rentGameDTOS) {
                Game game = gameService.getGameById(Long.parseLong(rentGameDTO.getId()));
                Date currentDate10 = add10DaysDate(saleOrder.getOrderDate());
                RentedGame rentedGame = new RentedGame(rentOrder, game, rentGameDTO.getPrice(), 0, false, false, saleOrder.getOrderDate(), currentDate10);
                rentedGameService.save(rentedGame);
            }
            ModelAndView mv = new ModelAndView("confirmation");
            rentGameDTOS.clear();
            sessionGameDTOS.clear();
            session.setAttribute("RentGameDTOS",rentGameDTOS);
            session.setAttribute("test",sessionGameDTOS);
            RentOrder userRentOrder = rentOrderService.findById(rentOrder.getRentOrderID()).get();
            List<RentedGame> rentedGames = rentedGameService.getRentedGamesFromRentOrder(userRentOrder);
            mv.addObject("rentedGames",rentedGames);
            mv.addObject("rentOrder",userRentOrder);
            mv.addObject("saleOrder",saleOrder);
            return mv;
        }
        catch (ParseException parseException){
            ModelAndView mv = new ModelAndView("order");
            return mv;
        }

    }
    public Date add10DaysDate(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 10);
        return c.getTime();
    }
}
