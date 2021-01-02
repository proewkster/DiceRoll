package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.RentOrder;
import be.thomasmore.graduaten.diceroll.entity.RentedGame;
import be.thomasmore.graduaten.diceroll.repository.RentedGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentedGameService {
    @Autowired
    RentedGameRepository repository;

 public void save(RentedGame rentedGame){
     repository.save(rentedGame);
 }
 public List<RentedGame> getRentedGamesFromRentOrder(RentOrder rentOrder){

     return repository.getRentedGamesByRentOrder(rentOrder.getRentOrderID());
 }
}
