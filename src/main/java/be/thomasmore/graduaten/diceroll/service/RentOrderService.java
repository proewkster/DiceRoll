package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.RentOrder;
import be.thomasmore.graduaten.diceroll.repository.RentOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentOrderService {
    @Autowired
    RentOrderRepository repository;

    public void Save(RentOrder rentOrder){
        repository.save(rentOrder);
    }
}
