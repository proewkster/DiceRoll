package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.RentedGame;
import be.thomasmore.graduaten.diceroll.repository.RentedGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentedGameService {
    @Autowired
    RentedGameRepository repository;

 public void Save(RentedGame rentedGame){
     repository.save(rentedGame);
 }
}
