package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.SoldGame;
import be.thomasmore.graduaten.diceroll.repository.SoldGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoldGameService {
    @Autowired
    SoldGameRepository soldGameRepository;

    public void SaveSoldGame(SoldGame soldGame){
        soldGameRepository.save(soldGame);
    }
}
