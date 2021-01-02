package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.*;
import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import be.thomasmore.graduaten.diceroll.objects.RentGameDTO;
import be.thomasmore.graduaten.diceroll.objects.SessionGameDTO;
import be.thomasmore.graduaten.diceroll.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    @Autowired
    GameService gameService;

    @RequestMapping(value = "/winkelmand",method = RequestMethod.GET)
    public ModelAndView ListSoldGames(ModelMap model, HttpServletRequest request){
       ModelAndView mv = new ModelAndView("winkelmand");
        User authUser = UserInformation.getAuthenticatedUser();
        mv.addObject("authUser", authUser);
       return mv;
    }

    @RequestMapping("/delitemwinkelmand")
    public ModelAndView delitemwinkelmand(HttpSession session, @RequestParam int id,@RequestParam(value="buy",required = false,defaultValue = "0") Integer buy){
        List<SessionGameDTO> testen = (List<SessionGameDTO>)session.getAttribute("test");
        List<RentGameDTO> rentGameDTOS = (List<RentGameDTO>) session.getAttribute("RentGameDTOS");
        ModelAndView mv = new ModelAndView("winkelmand");
        if (buy == 1){
        testen.remove(id);
        }else{
            rentGameDTOS.remove(id);
        }
        User authUser = UserInformation.getAuthenticatedUser();
        mv.addObject("authUser", authUser);
        return mv;
    }
    @RequestMapping("/additemwinkelmand")
    public ModelAndView additemwinkelmand(HttpSession session, @RequestParam int id,@RequestParam(value="buy",required = false,defaultValue = "0") Integer buy){
        ModelAndView mv = new ModelAndView("winkelmand");
        if (buy == 1) {
           List<SessionGameDTO> testen = (List<SessionGameDTO>) session.getAttribute("test");
           SessionGameDTO test = testen.get(id);
           Game game = gameService.getGameById(Long.parseLong(test.getId()));
           if (game.getStock_Sale() - (test.getAmount() + 1) >= 0) {
               test.setAmount(test.getAmount() + 1);
               testen.set(id, test);
           }
       }else {
            List<RentGameDTO> rentGameDTOS = (List<RentGameDTO>) session.getAttribute("RentGameDTOS");
            RentGameDTO rentGameDTO = rentGameDTOS.get(id);
            Game game = gameService.getGameById(Long.parseLong(rentGameDTO.getId()));
            if (game.getStock_Rent() - (rentGameDTO.getAantal() + 1) >= 0) {
                rentGameDTO.setAantal(rentGameDTO.getAantal() + 1);
                rentGameDTOS.set(id, rentGameDTO);
            }
        }
        User authUser = UserInformation.getAuthenticatedUser();
        mv.addObject("authUser", authUser);
        return mv;
    }
    @RequestMapping("/minitemwinkelmand")
    public ModelAndView minitemwinkelmand(HttpSession session, @RequestParam int id,@RequestParam(value="buy",required = false,defaultValue = "0") Integer buy){
        ModelAndView mv = new ModelAndView("winkelmand");
        if (buy == 1) {
            List<SessionGameDTO> testen = (List<SessionGameDTO>) session.getAttribute("test");
            SessionGameDTO test = testen.get(id);
            if (test.getAmount() - 1 != 0) {
                test.setAmount(test.getAmount() - 1);
                testen.set(id, test);
            }
        }else {
            List<RentGameDTO> rentGameDTOS = (List<RentGameDTO>) session.getAttribute("RentGameDTOS");
            RentGameDTO rentGameDTO = rentGameDTOS.get(id);
            if (rentGameDTO.getAantal() - 1 != 0) {
                rentGameDTO.setAantal(rentGameDTO.getAantal() - 1);
                rentGameDTOS.set(id, rentGameDTO);
            }
        }
        User authUser = UserInformation.getAuthenticatedUser();
        mv.addObject("authUser", authUser);
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
        orderService.save(saleOrder);
        List<SessionGameDTO> testen =(List<SessionGameDTO>) session.getAttribute("test");
        for (SessionGameDTO test:testen) {
            SoldGame soldGame = new SoldGame();
            soldGame.setAmount(test.getAmount());
            Game game = service.getGameById(Long.parseLong(test.getId()));
            soldGame.setGame(game);
            service.adjustStockGame(game,test.getAmount());
            soldGame.setSaleOrder(saleOrder);
            soldGame.setPricePaid(game.getPrice_Sale());
            soldGame.setDiscount(5);
            soldGameService.saveSoldGame(soldGame);
        }
        RentOrder rentOrder = new RentOrder(user,true);
        rentOrderService.save(rentOrder);
        List<RentGameDTO> rentGameDTOS = (List<RentGameDTO>) session.getAttribute("RentGameDTOS");
        for (RentGameDTO rentGameDTO:rentGameDTOS) {
            Game game = service.getGameById(Long.parseLong(rentGameDTO.getId()));
            Date currentdate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(currentdate);
            c.add(Calendar.DATE,10);
            Date currentDate10 = c.getTime();
            RentedGame rentedGame = new RentedGame(rentOrder,game,game.getPrice_Rent(),5,false,false,new Date(),currentDate10);
            rentedGameService.save(rentedGame);
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
        rentOrder.setUser(userService.findUserById(5).get());
        rentOrderService.save(rentOrder);
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
    @RequestMapping("/koopResterend")
    public ModelAndView koopResterend(HttpSession session, @RequestParam String id,@RequestParam int aantal,@RequestParam(value="buy",required = false,defaultValue = "0") Integer buy){
        ModelAndView mv = new ModelAndView("winkelmand");
        Game game = gameService.getGameById(Long.parseLong(id));
        if (buy == 1) {
            ArrayList<SessionGameDTO> testen = (ArrayList<SessionGameDTO>) session.getAttribute("test");
            for (SessionGameDTO test : testen) {
                if (game.getGameID() == Long.parseLong(test.getId())) {
                    test.setAmount(aantal);
                    session.setAttribute("test", testen);
                    ModelAndView mav = new ModelAndView("winkelmand");
                    return mav;
                }
            }
            SessionGameDTO test1 = new SessionGameDTO(id, game.getTitle(), aantal, game.getPrice_Sale());
            testen.add(test1);
            session.setAttribute("test", testen);
        }
        else{
            ArrayList<RentGameDTO> rentGameDTOS = (ArrayList<RentGameDTO>) session.getAttribute("RentGameDTOS");
            for (RentGameDTO rentGameDTO : rentGameDTOS) {
                if (game.getGameID() == Long.parseLong(rentGameDTO.getId())) {
                    rentGameDTO.setAantal(aantal);
                    session.setAttribute("RentGameDTOS", rentGameDTOS);
                    ModelAndView mav = new ModelAndView("winkelmand");
                    return mav;
                }
            }
            RentGameDTO rentGameDTO = new RentGameDTO(id, game.getTitle(),game.getPrice_Rent(),aantal);
            rentGameDTOS.add(rentGameDTO);
            session.setAttribute("RentGameDTOS", rentGameDTOS);
        }
        User authUser = UserInformation.getAuthenticatedUser();
        mv.addObject("authUser", authUser);
        return mv;
    }

}
