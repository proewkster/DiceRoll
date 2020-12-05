package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.*;
import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import be.thomasmore.graduaten.diceroll.objects.RentGameDTO;
import be.thomasmore.graduaten.diceroll.objects.TestDTO;
import be.thomasmore.graduaten.diceroll.repository.RentOrderRepository;
import be.thomasmore.graduaten.diceroll.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@Transactional
public class WinkelmandController {
    @Autowired
    GameService service;
    @Autowired
    SaleOrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    SoldGameService soldGameService;
    @Autowired
    RentOrderService rentOrderService;
    @Autowired
    RentedGameService rentedGameService;

    @RequestMapping(value = "/winkelmand",method = RequestMethod.GET)
    public ModelAndView ListSoldGames(ModelMap model, HttpServletRequest request){
       ModelAndView mv = new ModelAndView("winkelmand");
       return mv;
    }

    @RequestMapping("/delwinkelmand")
    public ModelAndView DelListSoldGames(HttpSession session){
        ModelAndView mv = new ModelAndView("confirmation");
        User user = UserInformation.getAuthenticatedUser();
        mv.addObject("authUser",user);
        if (user == null){
            return new ModelAndView("login");
        }
        SaleOrder saleOrder = new SaleOrder();
        saleOrder.setUser(UserInformation.getAuthenticatedUser());
        saleOrder.setOrderDate(new Date());
        saleOrder.setPaid(true);
        saleOrder.setDelivered(false);
        orderService.Save(saleOrder);
        List<TestDTO> testen =(List<TestDTO>) session.getAttribute("test");
        for (TestDTO test:testen) {
            SoldGame soldGame = new SoldGame();
            soldGame.setAmount(test.getAantal());
            Game game = service.getGameById(Long.parseLong(test.getId()));
            soldGame.setGame(game);
            service.adjustStockGame(game,test.getAantal());
            soldGame.setSaleOrder(saleOrder);
            soldGame.setPricePaid(game.getPrice_Sale() * test.getAantal());
            soldGame.setDiscount(5);
            soldGameService.SaveSoldGame(soldGame);
        }
        RentOrder rentOrder = new RentOrder(user,true);
        rentOrderService.Save(rentOrder);
        List<RentGameDTO> rentGameDTOS = (List<RentGameDTO>) session.getAttribute("RentGameDTOS");
        for (RentGameDTO rentGameDTO:rentGameDTOS) {
            Game game = service.getGameById(Long.parseLong(rentGameDTO.getId()));
            Date currentdate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(currentdate);
            c.add(Calendar.DATE,10);
            Date currentDate10 = c.getTime();
            RentedGame rentedGame = new RentedGame(rentOrder,game,game.getPrice_Rent(),5,false,false,new Date(),currentDate10);
            rentedGameService.Save(rentedGame);
        }
        testen.clear();
        rentGameDTOS.clear();
        session.setAttribute("RentGameDTOS",rentGameDTOS);
        session.setAttribute("test",testen);
        return mv;
    }

    @RequestMapping("/testOrder")
    public ModelAndView TestOrder(){
        ModelAndView mv = new ModelAndView("redirect:/");
        RentOrder rentOrder = new RentOrder();
        rentOrder.setPaid(true);
        rentOrder.setUser(userService.GetUser(5));
        rentOrderService.Save(rentOrder);
        return mv;
    }
    @RequestMapping("/testSolGame")
        public ModelAndView TestSoldGame(){
        ModelAndView mv = new ModelAndView("index");
        RentedGame rentedGame = new RentedGame();
        rentedGame.setDelivered(true);
        rentedGame.setDiscount(5);
        rentedGame.setEndDate(new Date());
        //soldGame.setSaleOrder(orderService.GetSaleOder(3));
        //soldGame.setGame(service.getGameById( Long.parseLong("8410")));
        //soldGameService.SaveSoldGame(soldGame);
        return mv;
    }
}
