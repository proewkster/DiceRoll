package be.thomasmore.graduaten.diceroll.controller;

import be.thomasmore.graduaten.diceroll.entity.RentOrder;
import be.thomasmore.graduaten.diceroll.entity.SaleOrder;
import be.thomasmore.graduaten.diceroll.entity.SoldGame;
import be.thomasmore.graduaten.diceroll.entity.User;
import be.thomasmore.graduaten.diceroll.helper.UserInformation;
import be.thomasmore.graduaten.diceroll.objects.RentGameDTO;
import be.thomasmore.graduaten.diceroll.objects.SessionGameDTO;
import be.thomasmore.graduaten.diceroll.objects.SoldGameDTO;
import be.thomasmore.graduaten.diceroll.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    GameService gameService;
    @GetMapping("/order")
    public ModelAndView order(HttpSession session){
        ModelAndView mv = new  ModelAndView("order");
        User authUser = UserInformation.getAuthenticatedUser();
        List<RentGameDTO> rentGameDTOS = (List<RentGameDTO>)session.getAttribute("RentGameDTOS");
        List<SessionGameDTO> testen = (List<SessionGameDTO>) session.getAttribute("test");
        SaleOrder saleOrder = new SaleOrder();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        mv.addObject("date",date);
        List<SoldGameDTO> soldGameDTOS = new ArrayList<SoldGameDTO>();
        for (SessionGameDTO sessionGameDTO :testen)
        {
            SoldGameDTO soldGameDTO = new SoldGameDTO();
            soldGameDTO.setAmount(sessionGameDTO.getAmount());
            soldGameDTO.setDiscount(sessionGameDTO.getDiscount());
            soldGameDTO.setPricePaid(sessionGameDTO.getPrice());
            soldGameDTO.setGame(gameService.getGameById(Long.parseLong(sessionGameDTO.getId())));
            soldGameDTOS.add(soldGameDTO);
        }
        mv.addObject("soldGameDTOS",soldGameDTOS);
        mv.addObject("saleOrder",saleOrder);
        mv.addObject("rentGameDTOS",rentGameDTOS);
        mv.addObject("authUser", authUser);
        return mv;
    }
    @PostMapping("/order")
    public ModelAndView orderresult(BindingResult bindingResult, @Valid RentOrder rentOrder)
    {
        ModelAndView mv = new ModelAndView("order");
        return mv;
    }
}
