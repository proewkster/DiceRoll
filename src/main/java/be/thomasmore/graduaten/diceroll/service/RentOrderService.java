package be.thomasmore.graduaten.diceroll.service;

import be.thomasmore.graduaten.diceroll.entity.RentOrder;
import be.thomasmore.graduaten.diceroll.objects.RentOrderFilter;
import be.thomasmore.graduaten.diceroll.repository.RentOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentOrderService {
    @Autowired
    RentOrderRepository repository;

    public Optional<RentOrder> findById(int id) { return repository.findById(id); }

    public Page<RentOrder> findAll(RentOrderFilter filter, Pageable pageable) { return repository.findAll(filter, pageable); }

    public void save(RentOrder rentOrder){
        repository.save(rentOrder);
    }
}
